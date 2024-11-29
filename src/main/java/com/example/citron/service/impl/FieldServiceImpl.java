package com.example.citron.service.impl;

import com.example.citron.domaine.Farm;
import com.example.citron.domaine.Field;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.FieldRepository;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.repository.TreeRepository;
import com.example.citron.service.FarmService;
import com.example.citron.service.FieldService;
import com.example.citron.web.errors.farm.FarmFieldLimitException;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.field.FieldAreaSuperieurCinquanteException;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.field.TotalFieldAreaExceedsFarmAreaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmService farmService;
    private final HarvestDetailRepository harvestDetailRepository;
    private final TreeRepository treeRepository;

    public FieldServiceImpl(FieldRepository fieldRepository, FarmService farmService,
                            HarvestDetailRepository harvestDetailRepository,
                            TreeRepository treeRepository) {
        this.fieldRepository = fieldRepository;
        this.farmService = farmService;
        this.harvestDetailRepository = harvestDetailRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public Field save(Field field) {

        Farm farm = farmService.findById(field.getFarm().getId().toString());

        if (farm == null) {
            throw new FarmNotFoundException("farm not found");
        }

        if (farm.getFields().size() >= 10) {
            throw new FarmFieldLimitException("farm cannot contain more than 10 fields");
        }

        if (field.getArea() > farm.getTotalArea() * 0.5) {
            throw new FieldAreaSuperieurCinquanteException("area superieur 50% de farm area");
        }


        double totalFieldArea = farm.getFields().stream()
                .mapToDouble(Field::getArea).sum();
        if (totalFieldArea + field.getArea() > farm.getTotalArea()) {
            throw new TotalFieldAreaExceedsFarmAreaException("total field area exceeds farm area");
        }


        field.setFarm(farm);
        return fieldRepository.save(field);
    }


    public Field findById(String id) {
        return fieldRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new FieldNotFoundException("field not found"));
    }


    @Override
    public Field update(UUID id, Field field) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("field not found"));

        Farm farm = farmService.findById(field.getFarm().getId().toString());
        if (farm == null) {
            throw new FarmNotFoundException("farm not found");
        }

        if (field.getArea() > farm.getTotalArea() * 0.5) {
            throw new FieldAreaSuperieurCinquanteException("area superieur 50% de farm area");
        }

        double totalFieldArea = farm.getFields().stream()
                .mapToDouble(Field::getArea).sum();

        if (totalFieldArea + field.getArea() - existingField.getArea() > farm.getTotalArea()) {
            throw new TotalFieldAreaExceedsFarmAreaException("total field area exceeds farm area");

        }

        existingField.setArea(field.getArea());
        existingField.setFarm(farm);
        return fieldRepository.save(existingField);

    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("field not found"));

        List<Tree> trees = field.getTrees();
        for (Tree tree : trees) {
            harvestDetailRepository.deleteByTreeId(tree.getId());
            treeRepository.delete(tree);
        }
        fieldRepository.delete(field);

    }

}