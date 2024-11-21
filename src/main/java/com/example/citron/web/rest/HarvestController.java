package com.example.citron.web.rest;

import com.example.citron.domaine.Harvest;
import com.example.citron.service.HarvestService;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.web.vm.HarvestVM;
import com.example.citron.web.vm.mapper.HarvestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/harvest")
public class HarvestController {
    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;

    public HarvestController(HarvestService harvestService, HarvestMapper harvestMapper) {
        this.harvestService = harvestService;
        this.harvestMapper = harvestMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<HarvestDTO> save(@RequestBody HarvestVM harvestVM){
        Harvest harvest = harvestMapper.toEntity(harvestVM);
        Harvest savedHarvest = harvestService.save(harvest);
        HarvestDTO harvestDTO = harvestMapper.toDTO(savedHarvest);
        return new ResponseEntity<>(harvestDTO, HttpStatus.CREATED);
    }
}
