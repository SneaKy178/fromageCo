package com.mfelton.model;

import com.mfelton.Enums.UserRole;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String prenom;
    protected String nom;
    protected String courriel;
    protected String password;
    protected LocalDate dateCreation;
    protected UserRole role;

    public User() {
        dateCreation = LocalDate.now();
    }

    public User(String prenom, String nom, String courriel, String password, UserRole role) {
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.password = password;
        this.dateCreation = LocalDate.now();
        this.role = role;
    }
}
