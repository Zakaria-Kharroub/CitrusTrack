package com.example.citron.service;

import com.example.citron.domaine.Farm;

import java.util.Optional;
import java.util.UUID;

public interface FarmService {
    Farm save(Farm farm);
    Farm findById(String id);
    Farm update(UUID id, Farm farm);

    Optional<Farm> search(Farm farm);
}
