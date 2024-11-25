package com.example.citron.domaine;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate saleDate;
    private Double quantity;
    private Double unitPrice;
    private String client;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;


//    @PrePersist
//    @PreUpdate
//    public void calculateRevenue() {
//        this.revenue = this.quantity * this.unitPrice;
//    }
}