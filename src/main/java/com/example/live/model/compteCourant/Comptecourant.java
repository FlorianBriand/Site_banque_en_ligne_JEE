package com.example.live.model.compteCourant;

import com.example.live.model.Portefeuille;

import javax.persistence.*;

@Entity
@Table(name = "comptecourant")
public class Comptecourant extends Portefeuille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomptecourant", nullable = false)
    private Integer id;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "intitule")
    private String intitule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public String toString() {
        return "Comptecourant{" +
                "id=" + id +
                ", montant=" + montant +
                ", intitule='" + intitule + '\'' +
                '}';
    }
}