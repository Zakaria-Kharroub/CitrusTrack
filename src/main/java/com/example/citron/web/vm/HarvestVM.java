package com.example.citron.web.vm;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class HarvestVM {

    private LocalDate harvestDate;
    private UUID fieldId;
}
