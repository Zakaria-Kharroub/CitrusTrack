package com.example.citron.service.impl;

import com.example.citron.domaine.*;
import com.example.citron.repository.HarvestRepository;
import com.example.citron.service.FarmService;
import com.example.citron.service.HarvestDetailService;
import com.example.citron.service.HarvestService;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.harvest.DuplicateSeasonHarvestException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HarvestServiceImpl implements HarvestService {
    private final HarvestRepository harvestRepository;
    private final FarmService farmService;
    private final HarvestDetailService harvestDetailService;

    public HarvestServiceImpl(HarvestRepository harvestRepository, FarmService farmService, HarvestDetailService harvestDetailService) {
        this.harvestRepository = harvestRepository;
        this.farmService = farmService;
        this.harvestDetailService = harvestDetailService;
    }

    @Override
    @Transactional
    public Harvest save(Harvest harvest){
        Farm farm = farmService.findById(harvest.getFarm().getId().toString());
        if (farm == null){
            throw new FarmNotFoundException("farm not found");
        }

//        validate one harvest per season

        boolean harvestExists = farm.getHarvests().stream()
                .anyMatch(hvst -> hvst.getSeason() == harvest.getSeason() );

        if (harvestExists){
            throw new DuplicateSeasonHarvestException("harvest deja existant pour cette saison");
        }

        return processHarvest(farm, harvest);

    }

    @Override
    @Transactional
    public Harvest processHarvest(Farm farm, Harvest harvest){
        // Set default harvest date if not provided
        if (harvest.getHarvestDate() == null){
            harvest.setHarvestDate(LocalDate.now());
        }

        harvest.setFarm(farm);
        List<HarvestDetail> harvestDetails = new ArrayList<>();
        double totalQuantite =0.0;

//        looper a les fields in  farm
        for (Field field : farm.getFields()){
//            looper a trees in field
            for (Tree tree : field.getTrees()){
                // cree harvest detail de chaque tree
                HarvestDetail harvestDetail = harvestDetailService.createHarvestDetail(tree,harvest);

                if (harvestDetail != null){
                    harvestDetails.add(harvestDetail);
                    totalQuantite += harvestDetail.getQuantity();
                }

            }
        }


//        calcul de total quantite
        harvest.setTotalQuantity(totalQuantite);
        harvest.setHarvestDetails(harvestDetails);

        return harvestRepository.save(harvest);


    }


}
