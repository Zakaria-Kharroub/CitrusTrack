package com.example.citron.web.vm;

import com.example.citron.service.dto.HarvestDetailDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class HarvestVM {

    private LocalDate dateRecolte;

    private UUID fieldId;
}