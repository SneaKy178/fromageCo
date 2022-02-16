package com.mfelton.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Paiements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String marque;
    protected int numCarte;
    protected LocalDate dateExpiration;
    protected String detenteurCarte;
    protected int cvv;
    protected String codePostale;

}
