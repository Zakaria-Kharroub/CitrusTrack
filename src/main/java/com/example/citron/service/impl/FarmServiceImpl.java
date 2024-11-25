package com.example.citron.service.impl;

import com.example.citron.domaine.Farm;
import com.example.citron.repository.FarmRepository;
import com.example.citron.repository.specification.FarmSpecification;
import com.example.citron.service.FarmService;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Farm findById(String id) {
        return farmRepository.findById(UUID.fromString(id))
                .orElseThrow(()->new FarmNotFoundException("farm not found"));
    }

    @Override
    public Farm update(UUID id, Farm farm) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(()->new FarmNotFoundException("farm not found"));

        existingFarm.setName(farm.getName());
        existingFarm.setLocation(farm.getLocation());
        existingFarm.setTotalArea(farm.getTotalArea());
        return farmRepository.save(existingFarm);
    }

    @Override
    public Optional<Farm> search(Farm farm) {
        return Optional.ofNullable(farmRepository.findOne(FarmSpecification.getFarmByCriteria(farm))
                .orElseThrow(() -> new FarmNotFoundException("farm not found")));
    }

}
