package com.example.citron.domaine;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class HarvestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double quantity;

    @ManyToOne
    private Harvest harvest;

    @ManyToOne
    private Tree tree;


}
