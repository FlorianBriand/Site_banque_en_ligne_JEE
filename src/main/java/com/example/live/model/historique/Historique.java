package com.example.live.model.historique;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historique")
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistorique", nullable = false)
    private Integer id;

    @Column(name = "idutilisateur")
    private Integer idutilisateur;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "date")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return "Historique{" +
                "id=" + id +
                ", idutilisateur='" + idutilisateur + '\'' +
                ", montant=" + montant +
                ", date=" + date +
                '}';
    }
}
