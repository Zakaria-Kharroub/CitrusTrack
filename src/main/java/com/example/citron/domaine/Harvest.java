package com.example.citron.domaine;

import com.example.citron.domaine.enums.Season;
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
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate harvestDate;

    @Enumerated(EnumType.STRING)
    private Season season;

    private Double totalQuantity;

    @ManyToOne
    private Farm farm;

    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails;
}