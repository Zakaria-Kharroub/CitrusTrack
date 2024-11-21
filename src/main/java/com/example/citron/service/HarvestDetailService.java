package com.example.citron.service;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.HarvestDetail;
import com.example.citron.domaine.Tree;

public interface HarvestDetailService {
    HarvestDetail save(HarvestDetail harvestDetail);
    HarvestDetail createHarvestDetail(Tree tree, Harvest harvest);
}
