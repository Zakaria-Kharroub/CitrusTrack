package com.example.citron.service.dto;

import com.example.citron.domaine.enums.Season;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class HarvestDTO {
    private UUID id;
    private UUID fieldId;
    private Season season;
    private double totalQuantity;
    private List<HarvestDetailDTO> harvestDetails;

}
