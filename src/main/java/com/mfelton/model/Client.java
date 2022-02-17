package com.mfelton.model;

import com.mfelton.Enums.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Client extends User implements Serializable {


    private String adresse;
    private String numTelephone;
    private String province;
    private String ville;


    @OneToMany
    private List<Paiements> listPaiements;

    public Client(){
        role = UserRole.CLIENT;
    }

    public Client(String prenom, String nom, String courriel, String password, String adresse, String numTelephone, String province, String ville) {
        super(prenom,nom,courriel,password, UserRole.CLIENT);
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.province = province;
        this.ville = ville;
    }
}
