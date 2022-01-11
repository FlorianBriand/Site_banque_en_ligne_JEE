package com.example.live.model;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilisateur", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 45)
    private String nom;

    @Column(name = "prenom", length = 45)
    private String prenom;

    @Column(name = "mdp", length = 45)
    private String mdp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enveloppe_idenveloppe", nullable = false)
    private Enveloppe enveloppeIdenveloppe;

    public Enveloppe getEnveloppeIdenveloppe() {
        return enveloppeIdenveloppe;
    }

    public void setEnveloppeIdenveloppe(Enveloppe enveloppeIdenveloppe) {
        this.enveloppeIdenveloppe = enveloppeIdenveloppe;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mdp='" + mdp + '\'' +
                ", enveloppeIdenveloppe=" + enveloppeIdenveloppe +
                '}';
    }
}