package com.example.citron.service;

import com.example.citron.domaine.Harvest;

import java.util.List;
import java.util.UUID;

public interface HarvestService {
    Harvest save(UUID fieldId, Harvest harvest);
    Harvest findById(UUID id);
    List<Harvest> findAll();
    boolean isValidHarvestForSeason(Harvest harvest);
}