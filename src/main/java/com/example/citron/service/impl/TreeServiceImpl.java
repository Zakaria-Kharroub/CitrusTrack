package com.example.citron.service.impl;

import com.example.citron.domaine.Field;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.TreeRepository;
import com.example.citron.service.FieldService;
import com.example.citron.service.TreeService;
import com.example.citron.web.errors.field.FieldNotFoundException;
import com.example.citron.web.errors.tree.NoSpaceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TreeServiceImpl implements TreeService {

    private final FieldService fieldService;
    private final TreeRepository treeRepository;

    public TreeServiceImpl(FieldService fieldService, TreeRepository treeRepository) {
        this.fieldService = fieldService;
        this.treeRepository = treeRepository;
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
    public List<Tree> findByFieldId(UUID fieldId) {
        return treeRepository.findByFieldId(fieldId);
    }


}
