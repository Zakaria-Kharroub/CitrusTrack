package com.example.citron.service.impl;

import com.example.citron.domaine.Field;
import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.HarvestDetail;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.FieldRepository;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.repository.HarvestRepository;
import com.example.citron.repository.TreeRepository;
import com.example.citron.service.HarvestService;
import com.example.citron.service.HarvestDetailService;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.harvest.DuplicateSeasonHarvestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final FieldRepository fieldRepository;
    private final TreeRepository treeRepository;
    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestDetailService harvestDetailService;

    public HarvestServiceImpl(HarvestRepository harvestRepository, FieldRepository fieldRepository, TreeRepository treeRepository, HarvestDetailRepository harvestDetailRepository, HarvestDetailService harvestDetailService) {
        this.harvestRepository = harvestRepository;
        this.fieldRepository = fieldRepository;
        this.treeRepository = treeRepository;
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestDetailService = harvestDetailService;
    }

    @Override
    @Transactional
    public Harvest save(Harvest harvest) {
        Field field = fieldRepository.findById(harvest.getField().getId())
                .orElseThrow(() -> new IllegalArgumentException("Field not found"));
        harvest.setField(field);

        if (harvestRepository.existsByFieldIdAndSaison(field.getId(), harvest.getSaison())) {
            throw new DuplicateSeasonHarvestException("Harvest already exists for this season");
        }

//        save harvest d'abord
        harvest.setQuantiteTotale(0.0);
        Harvest savedHarvest = harvestRepository.save(harvest);

        List<Tree> trees = treeRepository.findByFieldId(field.getId());
        double totalQuantity = 0.0;
        List<HarvestDetail> harvestDetails = new ArrayList<>();

        for (Tree tree : trees) {
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setHarvest(savedHarvest);
            harvestDetail.setTree(tree);

            if (tree.calculAge() > 20) {
                continue;
            }

            // Calculate quantity based on tree productivity
            double quantity = tree.calculPrductivite();
            harvestDetail.setQuantite(quantity);
            harvestDetails.add(harvestDetailRepository.save(harvestDetail));
            totalQuantity += quantity;
        }

//update quantite total
        savedHarvest.setQuantiteTotale(totalQuantity);
        savedHarvest.setHarvestDetails(harvestDetails);
        return harvestRepository.save(savedHarvest);
    }

    @Override
    public Harvest findById(UUID id) {
        return harvestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("harvest not found"));
    }

    @Override
    public List<Harvest> findAll() {
        return harvestRepository.findAll();
    }
}