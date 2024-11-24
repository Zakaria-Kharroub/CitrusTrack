package com.example.citron.service;

import com.example.citron.domaine.Harvest;

import java.time.LocalDate;
import java.util.UUID;

public interface HarvestService {


    Harvest harvestField(LocalDate harvestDate, UUID fieldId);
}
