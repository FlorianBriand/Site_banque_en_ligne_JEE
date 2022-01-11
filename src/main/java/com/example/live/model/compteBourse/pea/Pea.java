package com.example.live.model.compteBourse.pea;

import com.example.live.model.Portefeuille;
import com.example.live.model.compteBourse.Comptebourse;

import javax.persistence.*;

@Entity
@Table(name = "pea")
public class Pea extends Portefeuille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpea", nullable = false)
    private Integer id;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "intitule")
    private String intitule;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comptebourse_idcomptebourse", nullable = false)
    private Comptebourse comptebourseIdcomptebourse;

    public Comptebourse getComptebourseIdcomptebourse() {
        return comptebourseIdcomptebourse;
    }

    public void setComptebourseIdcomptebourse(Comptebourse comptebourseIdcomptebourse) {
        this.comptebourseIdcomptebourse = comptebourseIdcomptebourse;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pea{" +
                "id=" + id +
                ", montant=" + montant +
                ", intitule=" + intitule +
                ", comptebourseIdcomptebourse=" + comptebourseIdcomptebourse +
                '}';
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}