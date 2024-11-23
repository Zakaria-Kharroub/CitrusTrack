package com.example.citron.web.rest;

import com.example.citron.domaine.Harvest;
import com.example.citron.service.HarvestService;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.web.vm.HarvestVM;
import com.example.citron.web.vm.mapper.HarvestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<HarvestDTO> save(@Valid @RequestBody HarvestVM harvestVM) {
        Harvest harvest = harvestMapper.toEntity(harvestVM);
        Harvest savedHarvest = harvestService.save(harvest);
        return new ResponseEntity<>(harvestMapper.toDTO(savedHarvest), HttpStatus.CREATED);
    }
}