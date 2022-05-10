package com.mfelton.model;

import com.mfelton.Enums.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Administrateur extends User implements Serializable {

    private String titre;

    public Administrateur(String prenom, String nom, String courriel, String password, String titre) {
        super(prenom, nom, courriel, password, UserRole.ADMINISTRATEUR);
        this.titre = titre;
    }

    public Administrateur() {
        role = UserRole.ADMINISTRATEUR;
    }
}
