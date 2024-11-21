package com.example.citron.web.vm;

import com.example.citron.domaine.enums.Season;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class HarvestVM {
    private LocalDate harvestDate;
    private Season season;
    private UUID farmId;
}
