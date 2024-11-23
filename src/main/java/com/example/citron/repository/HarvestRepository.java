package com.example.citron.repository;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID> {
    @Query("SELECT COUNT(h) > 0 FROM Harvest h WHERE h.saison = :saison AND h.dateRecolte BETWEEN :startDate AND :endDate")
    boolean existsBySaisonAndDateRecolteInRange(
            @Param("saison") Season saison,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}