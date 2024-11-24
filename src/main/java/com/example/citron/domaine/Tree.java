package com.example.citron.domaine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate plantingDate;

    @ManyToOne
    private Field field;

    @OneToMany(mappedBy = "tree")
    private List<HarvestDetail> harvestDetails;



    public int calculAge(){
        if (plantingDate == null){
            return 0;
        }
        return Period.between(plantingDate,LocalDate.now()).getYears();
    }

    public double calculateProductivity() {
        int age = calculAge();
        if (age > 20) return 0.0;
        if (age < 3) return 2.5;
        else if (age <= 10) return 12.0;
        else return 20.0;
    }




}