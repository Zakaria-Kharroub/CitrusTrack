package com.example.citron.repository;

import com.example.citron.domaine.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, UUID> {

}
