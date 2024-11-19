package com.example.citron.domaine;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @NotNull
    String name;


    @NotBlank
    @NotNull
    String location;


    @NotBlank
    @NotNull
    Double totalArea;

    @NotNull
    @FutureOrPresent
    LocalDate creationDate;

    @OneToMany(mappedBy = "farm")
    private List<Field> fields;

    @OneToMany(mappedBy = "farm")
    private List<Harvest> harvests;

    public boolean isValidArea(double fieldAreaSum) {
        return fieldAreaSum < this.totalArea;
    }

}
