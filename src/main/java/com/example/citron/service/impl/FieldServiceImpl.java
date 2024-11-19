package com.example.citron.service.impl;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Field;
import com.example.citron.repository.FieldRepository;
import com.example.citron.service.FarmService;
import com.example.citron.service.FieldService;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.field.FieldAreaSuperieurCinquanteException;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmService farmService;

    public FieldServiceImpl(FieldRepository fieldRepository, FarmService farmService) {
        this.fieldRepository = fieldRepository;
        this.farmService = farmService;
    }

    @Override
    public Field save(Field field) {

        Farm farm = farmService.findById(field.getFarm().getId().toString());

        if (farm == null) {
            throw new FarmNotFoundException("farm not found");
        }

        if(field.getArea() > farm.getTotalArea() *0.5){
            throw  new FieldAreaSuperieurCinquanteException("area superieur 50% de farm area");
        }


        field.setFarm(farm);
        return fieldRepository.save(field);
    }
}