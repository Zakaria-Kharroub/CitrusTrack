package com.example.citron.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class HarvestDetailDTO {
    private UUID id;
    private UUID treeId;
    private double quantity;

}
