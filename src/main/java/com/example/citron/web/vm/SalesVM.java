package com.example.citron.web.vm;

import lombok.Data;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SalesVM {
    private LocalDate saleDate;
    private Double quantity;
    private Double unitPrice;
    private String client;
    private UUID harvestId;
}
