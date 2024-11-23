package com.example.citron.service.impl;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.HarvestDetail;
import com.example.citron.repository.HarvestRepository;
import com.example.citron.service.HarvestDetailService;
import com.example.citron.service.HarvestService;
import com.example.citron.util.SaisonUtil;
import com.example.citron.web.errors.harvest.DuplicateSeasonHarvestException;
import com.example.citron.web.errors.harvest.HarvestNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestDetailService harvestDetailService;

    public HarvestServiceImpl(HarvestRepository harvestRepository, @Lazy HarvestDetailService harvestDetailService) {
        this.harvestRepository = harvestRepository;
        this.harvestDetailService = harvestDetailService;
    }

    @Override
    public Harvest save(UUID fieldId, Harvest harvest) {
        harvest.setSaison(SaisonUtil.getSaisonFromDate(harvest.getDateRecolte()));
        harvest.setQuantiteTotale(0.0);

        if (!isValidHarvestForSeason(harvest)) {
            throw new DuplicateSeasonHarvestException("A harvest already exists for this season.");
        }

        Harvest savedHarvest = harvestRepository.save(harvest);

        // Save harvest details and calculate total quantity
        List<HarvestDetail> harvestDetails = harvestDetailService.save(fieldId, savedHarvest.getId());
        Double totalQuantity = harvestDetails.stream()
                .map(HarvestDetail::getQuantite)
                .reduce(0.0, Double::sum);
        savedHarvest.setQuantiteTotale(totalQuantity);

        return harvestRepository.save(savedHarvest);
    }

    @Override
    public Harvest findById(UUID id) {
        return harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("harevst not found"));
    }

    @Override
    public List<Harvest> findAll() {
        return harvestRepository.findAll();
    }

    @Override
    public boolean isValidHarvestForSeason(Harvest harvest) {
        return !harvestRepository.existsBySaisonAndDateRecolteInRange(
                harvest.getSaison(),
                harvest.getDateRecolte().withDayOfYear(1),
                harvest.getDateRecolte().withDayOfYear(harvest.getDateRecolte().lengthOfYear())
        );
    }
}