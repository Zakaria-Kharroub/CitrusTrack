package com.example.citron.service;

import com.example.citron.domaine.Tree;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface TreeService {
    Tree save(Tree tree);
    List<Tree> findByFieldId(UUID fieldId);

}
