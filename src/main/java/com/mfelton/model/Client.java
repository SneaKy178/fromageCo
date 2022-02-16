package com.mfelton.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Client extends User implements Serializable {


    private String adresse;
    private String numTelephone;
    private String province;
    private String ville;

    @OneToOne
    private Panier panier;

    @OneToMany
    private List<Paiements> listPaiements;

    public Client(){}

    public Client(String prenom, String nom, String courriel, String password, String adresse, String numTelephone, String province, String ville) {
        super(prenom,nom,courriel,password);
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.province = province;
        this.ville = ville;
    }
}
