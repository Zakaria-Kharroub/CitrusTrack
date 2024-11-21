package com.example.citron.service.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class HarvestDetailDTO {
    private UUID id;
    private Double quantity;
    private UUID treeId;
    private UUID harvestId;


}
