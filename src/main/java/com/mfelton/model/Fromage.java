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
    private String description;

    @Lob
    private byte[] data;

    public Fromage() {
    }

    public Fromage(String nom, double prix, String description, byte[] data) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;

        this.data = data;
    }
}
