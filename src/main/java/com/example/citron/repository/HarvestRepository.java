package com.example.citron.repository;

import com.example.citron.domaine.Harvest;
import com.example.citron.domaine.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, UUID> {
    @Query("SELECT COUNT(h) > 0 FROM Harvest h JOIN h.harvestDetails hd JOIN hd.tree t WHERE t.field.id = :fieldId AND h.season = :season")
    boolean existsByFieldIdAndSeason(@Param("fieldId") UUID fieldId, @Param("season") Season season);
}
