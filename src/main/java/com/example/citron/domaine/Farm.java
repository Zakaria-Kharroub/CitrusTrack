package com.example.citron.domaine;

import jakarta.persistence.*;
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

    private String name;
    private String location;
    private Double totalArea;
    private LocalDate creationDate;

    @OneToMany(mappedBy = "farm")
    private List<Field> fields;
}