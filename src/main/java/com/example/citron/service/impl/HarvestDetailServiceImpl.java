package com.example.citron.service.impl;

import com.example.citron.domaine.Field;
import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.HarvestDetail;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.service.FieldService;
import com.example.citron.service.HarvestDetailService;
import com.example.citron.service.HarvestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HarvestDetailServiceImpl implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;
    private final FieldService fieldService;
    private final HarvestService harvestService;

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository, FieldService fieldService, HarvestService harvestService) {
        this.harvestDetailRepository = harvestDetailRepository;
        this.fieldService = fieldService;
        this.harvestService = harvestService;
    }

    @Override
    public List<HarvestDetail> save(UUID fieldId, UUID harvestId) {
        Field field = fieldService.findById(String.valueOf(fieldId));
        Harvest harvest = harvestService.findById(harvestId);

        List<HarvestDetail> harvestDetails = new ArrayList<>();
        for (Tree tree : field.getTrees()) {
            if (harvestDetailRepository.existsByTree(tree)) {
                throw new IllegalArgumentException("Tree already harvested.");
            }

            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setHarvest(harvest);
            harvestDetail.setTree(tree);
            harvestDetail.setQuantite(tree.calculPrductivite());

            harvestDetails.add(harvestDetailRepository.save(harvestDetail));
        }

        return harvestDetails;
    }
}