package com.mfelton.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Paiements implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String marque;
    protected int numCarte;
    protected LocalDate dateExpiration;
    protected String detenteurCarte;
    protected int cvv;
    protected String codePostale;

    public Paiements(){}

    public Paiements(String marque, int numCarte, LocalDate dateExpiration, String detenteurCarte, int cvv, String codePostale) {
        this.marque = marque;
        this.numCarte = numCarte;
        this.dateExpiration = dateExpiration;
        this.detenteurCarte = detenteurCarte;
        this.cvv = cvv;
        this.codePostale = codePostale;
    }
}
