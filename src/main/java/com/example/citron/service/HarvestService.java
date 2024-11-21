package com.example.citron.service;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Harvest;

public interface HarvestService {
    Harvest save(Harvest harvest);
    Harvest processHarvest(Farm farm, Harvest harvest);
}
