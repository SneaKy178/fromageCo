package com.mfelton.model;

import com.mfelton.Enums.UserRole;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Client extends User implements Serializable {


    private String adresse;
    private String numTelephone;
    private String province;
    private String ville;


    @OneToOne(cascade= CascadeType.ALL)
    private Panier panier;

    public Client(){
        role = UserRole.CLIENT;
        panier = new Panier(0,0,Collections.emptyList());
    }

    public Client(String prenom, String nom, String courriel, String password, String adresse, String numTelephone, String province, String ville,Panier panier) {
        super(prenom,nom,courriel,password, UserRole.CLIENT);
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.province = province;
        this.ville = ville;
        this.panier = panier;
    }
}
