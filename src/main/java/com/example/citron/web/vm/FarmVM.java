package com.example.citron.web.vm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data

public class FarmVM {

    private String name;
    String location;

    Double totalArea;

    LocalDate creationDate;
    private List<FieldVM> fields;
}
