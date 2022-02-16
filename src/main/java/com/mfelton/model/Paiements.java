package com.mfelton.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Paiements implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marque;
    private int numCarte;
    private LocalDate dateExpiration;
    private String detenteurCarte;
    private int cvv;
    private String codePostale;

    @ManyToOne
    private Panier panier;

    public Paiements(){}

    public Paiements(String marque, int numCarte, LocalDate dateExpiration, String detenteurCarte, int cvv, String codePostale) {
        this.marque = marque;
        this.numCarte = numCarte;
        this.dateExpiration = dateExpiration;
        this.detenteurCarte = detenteurCarte;
        this.cvv = cvv;
        this.codePostale = codePostale;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
}
