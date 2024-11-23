package com.example.citron.repository;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID> {
    boolean existsByFieldIdAndSaison(UUID fieldId, Season saison);
}