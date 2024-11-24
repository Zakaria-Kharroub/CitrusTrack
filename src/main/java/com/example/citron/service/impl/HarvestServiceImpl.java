package com.example.citron.service.impl;

import com.example.citron.domaine.Field;
import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.HarvestDetail;
import com.example.citron.domaine.Tree;
import com.example.citron.domaine.enums.Season;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.repository.HarvestRepository;
import com.example.citron.service.FieldService;
import com.example.citron.service.HarvestService;
import com.example.citron.service.TreeService;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.harvest.HarvestExistInSeasonException;
import com.example.citron.web.errors.harvest.NoTreesAviableToHarvestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestDetailRepository harvestDetailRepository;

    private final TreeService treeService;

    private final FieldService fieldService;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestDetailRepository harvestDetailRepository, TreeService treeService, FieldService fieldService) {
        this.harvestRepository = harvestRepository;
        this.harvestDetailRepository = harvestDetailRepository;
        this.treeService = treeService;
        this.fieldService = fieldService;
    }


    @Override
    public Harvest harvestField(LocalDate harvestDate, UUID fieldId) {
        validateHarvestYear(harvestDate);
        Field field = validateFieldExistence(fieldId);
        validateFieldNotAlreadyHarvested(fieldId, harvestDate);
        List<Tree> trees = validateTreesInField(fieldId);
        List<HarvestDetail> harvestDetails = createHarvestDetailsForTrees(trees, harvestDate);

        if (harvestDetails.isEmpty()) {
            throw new IllegalArgumentException("No valid trees found for harvesting in the field.");
        }

        double totalQuantity = harvestDetails.stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();

        Harvest harvest = new Harvest();
        harvest.setHarvestDate(harvestDate);
        harvest.setSeason(determineSeason(harvestDate));
        harvest.setTotalQuantity(totalQuantity);
        harvest.setHarvestDetails(harvestDetails);

        Harvest savedHarvest = harvestRepository.save(harvest);
        saveHarvestDetails(savedHarvest, harvestDetails);

        return savedHarvest;
    }

    private void validateHarvestYear(LocalDate harvestDate) {
        int currentYear = LocalDate.now().getYear();
        if (harvestDate.getYear() != currentYear) {
            throw new IllegalArgumentException("Harvest year must be the current year.");
        }
    }

    private Field validateFieldExistence(UUID fieldId) {
        Field field =  fieldService.findById(String.valueOf(fieldId));
        if (field == null) {
                throw new FieldNotFoundException("Field not found with id: ");
        }else {
            return field;
        }
    }

    private void validateFieldNotAlreadyHarvested(UUID fieldId, LocalDate harvestDate) {
        Season currentSeason = determineSeason(harvestDate);
        boolean hasHarvest = harvestRepository.existsByFieldIdAndSeason(fieldId, currentSeason);
        if (hasHarvest) {
            throw new HarvestExistInSeasonException("deja harvest in this season");
        }
    }

    private List<Tree> validateTreesInField(UUID fieldId) {
        List<Tree> trees = treeService.findByFieldId(fieldId);
        if (trees.isEmpty()) {
            throw new NoTreesAviableToHarvestException("no trees aviable for harvest");
        }
        return trees;
    }

    private List<HarvestDetail> createHarvestDetailsForTrees(List<Tree> trees, LocalDate harvestDate) {
        List<HarvestDetail> harvestDetails = new ArrayList<>();
        Season currentSeason = determineSeason(harvestDate);

        for (Tree tree : trees) {
            if (tree.calculAge() > 20 || hasTreeBeenHarvestedInSeason(tree.getId(), currentSeason)) continue;
            double treeProductivity = treeService.calculateProductivity(tree.getId());
            HarvestDetail detail = new HarvestDetail();
            detail.setTree(tree);
            detail.setQuantity(treeProductivity);
            harvestDetails.add(detail);
        }
        return harvestDetails;
    }

    private boolean hasTreeBeenHarvestedInSeason(UUID treeId, Season season) {
        return harvestDetailRepository.existsByTreeIdAndHarvestSeason(treeId, season);
    }

    private void saveHarvestDetails(Harvest savedHarvest, List<HarvestDetail> harvestDetails) {
        for (HarvestDetail detail : harvestDetails) {
            detail.setHarvest(savedHarvest);
            harvestDetailRepository.save(detail);
        }
    }

    private Season determineSeason(LocalDate harvestDate) {
        int month = harvestDate.getMonthValue();
        if (month >= 3 && month <= 5) {
            return Season.SPRING;
        } else if (month >= 6 && month <= 8) {
            return Season.SUMMER;
        } else if (month >= 9 && month <= 11) {
            return Season.FALL;
        } else {
            return Season.WINTER;
        }
    }
}
