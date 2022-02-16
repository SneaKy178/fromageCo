package com.mfelton.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String adresse;
    protected String numTelephone;
    protected String province;
    protected String ville;

    public Client(int id, String adresse, String numTelephone, String province, String ville) {
        this.id = id;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.province = province;
        this.ville = ville;
    }
}
