package com.example.citron.service;

import com.example.citron.domaine.HarvestDetail;

import java.util.List;
import java.util.UUID;

public interface HarvestDetailService {
    List<HarvestDetail> save(UUID fieldId, UUID harvestId);
}