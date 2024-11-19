package com.example.citron.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FarmDTO {
    private UUID id;
    private String name;
    private String location;
    private Double totalArea;
    private LocalDate creationDate;
    private List<FieldDTO> fields;
}