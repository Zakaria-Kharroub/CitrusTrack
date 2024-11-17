package com.example.citron.service.impl;

import com.example.citron.domaine.Farm;
import com.example.citron.repository.FarmRepository;
import com.example.citron.service.FarmService;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository=farmRepository ;
    }

    @Override
    public Farm save(Farm farm) {
        return farmRepository.save(farm);
    }

}
