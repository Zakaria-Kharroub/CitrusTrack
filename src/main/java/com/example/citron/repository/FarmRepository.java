package com.example.citron.repository;

import com.example.citron.domaine.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FarmRepository extends JpaRepository<Farm, UUID>, JpaSpecificationExecutor<Farm> {

//    @Query("SELECT DISTINCT f from Farm f"+
//            "Join f.tree t"+
//            "Join t.harvestDetail hd"
//    );

}
