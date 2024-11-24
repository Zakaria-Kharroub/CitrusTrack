package com.example.citron.web.rest;

import com.example.citron.domaine.Sales;
import com.example.citron.service.SalesService;
import com.example.citron.service.dto.SalesDTO;
import com.example.citron.web.vm.SalesVM;
import com.example.citron.web.vm.mapper.SalesMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;
    private final SalesMapper salesMapper;

    public SalesController(SalesService salesService, SalesMapper salesMapper) {
        this.salesService = salesService;
        this.salesMapper = salesMapper;
    }

    @PostMapping("/save/{harvestId}")
    public ResponseEntity<SalesDTO> save(@Valid @RequestBody SalesVM salesVM, @PathVariable UUID harvestId) {
        Sales sales = salesMapper.toEntity(salesVM);
        Sales savedSales = salesService.save(sales, harvestId);
        SalesDTO salesDTO = salesMapper.toDTO(savedSales);
        return new ResponseEntity<>(salesDTO, HttpStatus.CREATED);
    }
}