package com.example.citron.web.vm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TreeVM {
    private LocalDate plantingDate;
    private UUID fieldId;
}
