package com.example.citron.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmDTO {
    private UUID id;
    private String name;
    private String location;
    private Double totalArea;
    private LocalDate creationDate;
}