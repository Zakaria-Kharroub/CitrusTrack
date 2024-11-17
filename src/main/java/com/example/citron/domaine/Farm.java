package com.example.citron.domaine;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String name;

    String location;

    Double totalArea;

    LocalDate creationDate;

    @OneToMany(mappedBy = "farm")
    private List<Field> fields;

    @OneToMany(mappedBy = "farm")
    private List<Harvest> harvests;

}
