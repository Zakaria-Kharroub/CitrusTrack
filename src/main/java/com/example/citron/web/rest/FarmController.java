package com.example.citron.web.rest;

import com.example.citron.domaine.Farm;
import com.example.citron.service.FarmService;
import com.example.citron.service.dto.FarmDTO;
import com.example.citron.web.vm.FarmVM;
import com.example.citron.web.vm.mapper.FarmMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<FarmDTO> save(@RequestBody FarmVM farmVM){
        Farm farm = farmMapper.toEntity(farmVM);
        Farm savedFarm = farmService.save(farm);
        FarmDTO farmDTO = farmMapper.toDTO(savedFarm);
        return new ResponseEntity<>(farmDTO, HttpStatus.CREATED);
    }
}