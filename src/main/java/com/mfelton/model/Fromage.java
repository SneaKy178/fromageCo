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
    private int vieillesse;
    private int quantite;

    @Lob
    private byte[] data;

    public Fromage(){}

    public Fromage(String nom, double prix, int vieillesse, int quantite, byte[] data) {
        this.nom = nom;
        this.prix = prix;
        this.vieillesse = vieillesse;
        this.quantite = quantite;
        this.data = data;
    }
}
