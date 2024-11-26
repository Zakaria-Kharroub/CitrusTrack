package com.example.citron.service.dto;

import com.example.citron.domaine.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
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
    private List<Field> fields;
}