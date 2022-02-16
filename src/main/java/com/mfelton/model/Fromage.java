package com.mfelton.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Fromage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private int prix;
    private int vieillese;
    private int quantite;

    public Fromage(){}

    public Fromage(String nom, int prix, int vieillese, int quantite) {
        this.nom = nom;
        this.prix = prix;
        this.vieillese = vieillese;
        this.quantite = quantite;
    }
}
