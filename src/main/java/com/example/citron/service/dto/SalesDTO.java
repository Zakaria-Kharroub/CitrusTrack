package com.example.citron.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SalesDTO {
    private LocalDate saleDate;
    private Double quantity;
    private Double unitPrice;
    private String client;
    private UUID harvestId;
}