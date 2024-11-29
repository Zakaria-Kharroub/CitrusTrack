package com.example.citron.web.rest;

import com.example.citron.domaine.Harvest;
import com.example.citron.service.HarvestService;
import com.example.citron.service.dto.HarvestDTO;
import com.example.citron.web.vm.HarvestVM;
import com.example.citron.web.vm.mapper.HarvestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<HarvestDTO> harvestField(@Valid @RequestBody HarvestVM requestVM) {
        Harvest harvest = harvestService.harvestField(requestVM.getHarvestDate(), requestVM.getFieldId());
        HarvestDTO harvestDTO = harvestMapper.toDTO(harvest);
        return ResponseEntity.ok().body(harvestDTO);
    }

 @GetMapping("/find/{id}")
public ResponseEntity<HarvestDTO> findById(@PathVariable UUID id) {
    Optional<Harvest> harvest = harvestService.findById(id);
    if (harvest.isPresent()) {
        HarvestDTO harvestDTO = harvestMapper.toDTO(harvest.get());
        return ResponseEntity.ok().body(harvestDTO);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
