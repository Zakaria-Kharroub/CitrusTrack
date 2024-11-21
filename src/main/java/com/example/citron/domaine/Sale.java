package com.example.citron.domaine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate saleDate;

    private Double unitPrice;

    private Double quantity;

    private String customerName;

    @ManyToOne
    private HarvestDetail harvestDetail;

    public Double calculateRevenue() {
        return quantity * unitPrice;
    }
}