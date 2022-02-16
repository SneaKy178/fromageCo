package com.mfelton.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Client extends User implements Serializable {


    protected String adresse;
    protected String numTelephone;
    protected String province;
    protected String ville;

    public Client(){}

    public Client(String prenom, String nom, String courriel, String password, String adresse, String numTelephone, String province, String ville) {
        super(prenom,nom,courriel,password);
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.province = province;
        this.ville = ville;
    }
}
