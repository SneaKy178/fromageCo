package com.mfelton.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Panier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nbrFromage;
    private double prixTotalAvantTaxes;
    private double prixTotalApresTaxes;

    @OneToMany
    private List<Fromage> listeFromages;

    public Panier(){}
    public Panier(int nbrFromage, double prixTotalAvantTaxes, double prixTotalApresTaxes, List<Fromage> listeFromages) {
        this.nbrFromage = nbrFromage;
        this.prixTotalAvantTaxes = prixTotalAvantTaxes;
        this.prixTotalApresTaxes = prixTotalApresTaxes;
        this.listeFromages = listeFromages;
    }
}
