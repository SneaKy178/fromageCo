package com.mfelton.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected int nbrFromage;
    protected double prixTotalAvantTaxes;
    protected double prixTotalApresTaxes;

    @OneToMany
    protected List<Fromage> listeFromages;
}
