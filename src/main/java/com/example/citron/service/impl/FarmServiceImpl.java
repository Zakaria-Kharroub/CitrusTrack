package com.example.citron.service.impl;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Field;
import com.example.citron.repository.FarmRepository;
import com.example.citron.repository.FieldRepository;
import com.example.citron.service.FarmService;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;
    private final FieldRepository fieldRepository;


    public FarmServiceImpl(FarmRepository farmRepository, FieldRepository fieldRepository) {
        this.farmRepository=farmRepository ;
        this.fieldRepository = fieldRepository;
    }


    @Override
    public Farm save(Farm farm) {
        return saveFromDTO(farm);
    }

    public Farm saveFromDTO(Farm farm) {
        Farm savedFarm = farmRepository.save(farm);

        if (savedFarm.getFields() != null) {
            double totalFieldArea = savedFarm.getFields().stream()
                    .mapToDouble(Field::getArea)
                    .sum();

            if (!savedFarm.isValidArea(totalFieldArea)) {
                throw new IllegalArgumentException("Total area of fields exceeds farm area");
            }

            List<Field> savedFields = savedFarm.getFields().stream()
                    .map(field -> {
                        field.setFarm(savedFarm);
                        return fieldRepository.save(field);
                    })
                    .toList();

            savedFarm.setFields(savedFields);
        }

        return savedFarm;
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

}
