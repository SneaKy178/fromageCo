package com.mfelton.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Panier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nbrFromage;
    private double prixTotal;

    @OneToMany
    private List<Fromage> listeFromages;

    public Panier(){}
    public Panier(int nbrFromage, double prixTotal, List<Fromage> listeFromages) {
        this.nbrFromage = nbrFromage;
        this.prixTotal = prixTotal;
        this.listeFromages = listeFromages;}

}
