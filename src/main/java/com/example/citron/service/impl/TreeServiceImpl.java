package com.example.citron.service.impl;

import com.example.citron.domaine.Field;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.HarvestDetailRepository;
import com.example.citron.repository.TreeRepository;
import com.example.citron.service.FieldService;
import com.example.citron.service.TreeService;
import com.example.citron.web.errors.farm.FarmNotFoundException;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.tree.NoSpaceException;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class TreeServiceImpl implements TreeService {

    private final FieldService fieldService;
    private final TreeRepository treeRepository;
    private final HarvestDetailRepository harvestDetailRepository;

    public TreeServiceImpl(FieldService fieldService, TreeRepository treeRepository,
                           HarvestDetailRepository harvestDetailRepository) {
        this.fieldService = fieldService;
        this.treeRepository = treeRepository;
        this.harvestDetailRepository = harvestDetailRepository;
    }

    @Override
    public Tree save(Tree tree) {
        Field field = fieldService.findById(tree.getField().getId().toString());
        if (field == null){
            throw new FieldNotFoundException("field not found");
        }


//        check space if aviable ou non

        double maxTrees = field.getArea() / 100;
        long treesCount = treeRepository.countByFieldId(field.getId());
        if (treesCount >= maxTrees){
            throw new NoSpaceException("no space for tree");
        }


        tree.setField(field);
        return treeRepository.save(tree);
    }

    @Override
    public double calculateProductivity(UUID treeId) {
        Tree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + treeId));
        return tree.calculateProductivity();
    }

    @Override
    public List<Tree> findByFieldId(UUID fieldId) {
        return treeRepository.findByFieldId(fieldId);
    }

    @Override
    public void deleteById(UUID id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(()->new FarmNotFoundException("tree not found"));

        harvestDetailRepository.deleteByTreeId(tree.getId());
        treeRepository.delete(tree);

    }


}
