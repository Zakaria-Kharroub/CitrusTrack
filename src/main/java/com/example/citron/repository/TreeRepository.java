package com.example.citron.repository;

import com.example.citron.domaine.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TreeRepository extends JpaRepository<Tree, UUID> {
    long countByFieldId(UUID fieldId);

    List<Tree> findByFieldId(UUID treeId);
}
