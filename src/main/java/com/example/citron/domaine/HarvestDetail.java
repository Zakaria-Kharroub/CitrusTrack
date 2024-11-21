package com.example.citron.domaine;
import com.example.citron.domaine.Harvest;


import com.example.citron.domaine.Tree;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double quantity;

    @ManyToOne
    private Harvest harvest;

    @ManyToOne
    private Tree tree;

    @OneToMany(mappedBy = "harvestDetail")
    private List<Sale> sales;
}