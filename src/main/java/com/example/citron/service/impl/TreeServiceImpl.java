package com.example.citron.service.impl;

import com.example.citron.domaine.Field;
import com.example.citron.domaine.Tree;
import com.example.citron.repository.TreeRepository;
import com.example.citron.service.FieldService;
import com.example.citron.service.TreeService;
import com.example.citron.web.errors.field.FieldNotFoundException;
import org.springframework.stereotype.Service;

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
        tree.setField(field);
        return treeRepository.save(tree);
    }
}
