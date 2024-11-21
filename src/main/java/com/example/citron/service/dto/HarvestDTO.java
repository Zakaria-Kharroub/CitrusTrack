package com.example.citron.service.dto;

import com.example.citron.domaine.enums.Season;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class HarvestDTO {
    private UUID id;
    private LocalDate harvestDate;
    private Season season;
    private Double totalQuantity;
    private UUID farmId;
    private List<HarvestDetailDTO> harvestDetails;


}
