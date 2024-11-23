package com.example.citron.domaine;

import com.example.citron.domaine.enums.Season;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private Season saison;

    private LocalDate dateRecolte;
    private Double quantiteTotale;


    @OneToMany(mappedBy = "harvest", fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();


}