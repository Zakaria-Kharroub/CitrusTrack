package com.example.citron.web.vm;

import com.example.citron.domaine.enums.Season;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class HarvestVM {
    @NotNull(message = "Date de récolte ne peut pas être null")
    private LocalDate dateRecolte;

    @NotNull(message = "Saison ne peut pas être null")
    private Season saison;

    @NotNull(message = "Field ID ne peut pas être null")
    private UUID fieldId;
}
