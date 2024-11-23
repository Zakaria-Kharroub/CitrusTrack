package com.example.citron.service;

import com.example.citron.domaine.Tree;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;


public interface TreeService {
    Tree save(Tree tree);

    double calculateProductivity(UUID treeId);

    List<Tree> findByFieldId(UUID fieldId);
}
