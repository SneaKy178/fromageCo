package com.mfelton.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Administrateur extends User implements Serializable {

    protected String role;

    public Administrateur(String prenom, String nom, String courriel, String password, String role) {
        super(prenom,nom,courriel,password);
        this.role = role;
    }

    public Administrateur() {
    }
}
