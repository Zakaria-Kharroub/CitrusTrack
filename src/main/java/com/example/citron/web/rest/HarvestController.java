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

import java.util.NoSuchElementException;

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
    public ResponseEntity<?> createHarvest(@Valid @RequestBody HarvestVM harvestVM) {
        try {
            Harvest harvest = harvestMapper.toEntity(harvestVM);
            Harvest savedHarvest = harvestService.save(harvestVM.getFieldId(), harvest);
            return ResponseEntity.ok(harvestMapper.toVM(savedHarvest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the harvest: " + e.getMessage());
        }
    }
}