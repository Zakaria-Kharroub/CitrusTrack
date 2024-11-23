package com.example.citron.service.impl;

import com.example.citron.domaine.HarvestDetail;
import com.example.citron.domaine.Tree;
import com.example.citron.domaine.Harvest;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.service.HarvestDetailService;
import org.springframework.stereotype.Service;

@Service
public class HarvestDetailServiceImpl implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository) {
        this.harvestDetailRepository = harvestDetailRepository;
    }

    @Override
    public HarvestDetail save(HarvestDetail harvestDetail) {
        return harvestDetailRepository.save(harvestDetail);
    }

    @Override
    public HarvestDetail createHarvestDetail(Tree tree, Harvest harvest) {
        // Skip arbre non productive due to age > 20
        if (tree.calculAge() > 20) {
            return null;
        }

        HarvestDetail harvestDetail = new HarvestDetail();
        harvestDetail.setTree(tree);
        harvestDetail.setHarvest(harvest);

//        calcul de quantite base dans tree productivity
        harvestDetail.setQuantite(tree.calculPrductivite());
        return save(harvestDetail);
    }
}