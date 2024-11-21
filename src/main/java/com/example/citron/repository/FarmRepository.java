package com.example.citron.repository;

import com.example.citron.domaine.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FarmRepository extends JpaRepository<Farm, UUID> {
    Page<Farm> findAll(Pageable pageable);

}
