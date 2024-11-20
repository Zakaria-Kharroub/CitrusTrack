package com.example.citron.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TreeDTO {
    private UUID id;
    private LocalDate plantingDate;
    private UUID fieldId;
}
