package com.example.citron.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


import java.time.LocalDate;

@Data

public class FarmVM {

    @NotNull(message = "name obligatoire")
    @NotBlank(message = "name must be not empty")
    private String name;

    @NotNull(message = "location obligatoire")
    @NotBlank(message = "location must be not empl")
    String location;

    @NotNull(message = "totalArea obligatoire")
    @Positive(message = "totalArea must be positive")
    Double totalArea;

    @NotNull(message = "creationDate obligatoire")
    @NotBlank(message = "creationDate must be not empty")
    LocalDate creationDate;
}
