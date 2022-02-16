package com.mfelton.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String prenom;
    protected String nom;
    protected String courriel;
    protected String password;
    protected LocalDate dateCreation;

    public User() {
        dateCreation = LocalDate.now();
    }

    public User(int id, String prenom, String nom, String courriel, String password, LocalDate dateCreation) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.password = password;
        this.dateCreation = dateCreation;
    }
}
