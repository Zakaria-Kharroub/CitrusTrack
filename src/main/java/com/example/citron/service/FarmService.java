package com.example.citron.service;

import com.example.citron.domaine.Farm;

import java.util.UUID;

public interface FarmService {
    Farm save(Farm farm);
    Farm findById(String id);
}
