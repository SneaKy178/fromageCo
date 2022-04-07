package com.mfelton.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Paiement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marque;
    private long numCarte;
    private String dateExpiration;
    private String detenteurCarte;
    private int cvv;
    private String codePostale;

    @ManyToOne
    private Client client;

    public Paiement(){}

    public Paiement(String marque, long numCarte, String dateExpiration, String detenteurCarte, int cvv, String codePostale,Client client) {
        this.marque = marque;
        this.numCarte = numCarte;
        this.dateExpiration = dateExpiration;
        this.detenteurCarte = detenteurCarte;
        this.cvv = cvv;
        this.codePostale = codePostale;
        this.client = client;
    }

}
