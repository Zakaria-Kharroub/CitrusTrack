package com.example.citron.web.rest;

import com.example.citron.domaine.Farm;
import com.example.citron.service.FarmService;
import com.example.citron.service.dto.FarmDTO;
import com.example.citron.web.vm.FarmSearchVM;
import com.example.citron.web.vm.FarmVM;
import com.example.citron.web.vm.mapper.FarmMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/farm")
public class FarmController {
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    public FarmController(FarmService farmService, FarmMapper farmMapper) {
        this.farmService = farmService;
        this.farmMapper = farmMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FarmDTO> save(@RequestBody @Valid FarmVM farmVM) {
        Farm farm = farmMapper.toEntity(farmVM);
        Farm savedFarm = farmService.save(farm);
        FarmDTO farmDTO = farmMapper.toDTO(savedFarm);
        return new ResponseEntity<>(farmDTO, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FarmDTO> findById(@PathVariable UUID id){
        Farm farm = farmService.findById(id.toString());
        FarmDTO farmDTO = farmMapper.toDTO(farm);
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FarmDTO> update(@PathVariable UUID id,@RequestBody FarmVM farmVM){
        Farm farm = farmMapper.toEntity(farmVM);
        Farm updatedFarm = farmService.update(id,farm);
        FarmDTO farmDTO = farmMapper.toDTO(updatedFarm);
        return ResponseEntity.ok(farmDTO);
    }


    @PostMapping("/search")
    public ResponseEntity<FarmDTO> search(@RequestBody FarmSearchVM farmSearchVM){
        Farm farm = farmMapper.toEntity(farmSearchVM);
        Farm searchedFarm = farmService.search(farm)
                .orElseThrow(()->new RuntimeException("farm not found"));
        FarmDTO farmDTO = farmMapper.toDTO(searchedFarm);
        return ResponseEntity.ok(farmDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable UUID id) {
        farmService.deleteById(id);
        return ResponseEntity.ok("Farm deleted success");
    }














}