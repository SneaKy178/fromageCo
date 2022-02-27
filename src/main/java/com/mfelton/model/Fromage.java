package com.mfelton.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Fromage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private double prix;
    private int vieillese;
    private int quantite;

    @Lob
    private String data;

    public Fromage(){}

    public Fromage(String nom, double prix, int vieillese, int quantite, String data) {
        this.nom = nom;
        this.prix = prix;
        this.vieillese = vieillese;
        this.quantite = quantite;
        this.data = data;
    }
}
