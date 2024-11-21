package com.example.citron.service;

import com.example.citron.domaine.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FarmService {
    Farm save(Farm farm);
    Farm findById(String id);
    Farm update(UUID id, Farm farm);
    Page<Farm> findAll(Pageable pageable);



}
