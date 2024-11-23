package com.example.citron.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class HarvestDetailDTO {
    private UUID id;
    private UUID harvestId;
    private UUID treeId;
    private Float collectedQuantity;
}