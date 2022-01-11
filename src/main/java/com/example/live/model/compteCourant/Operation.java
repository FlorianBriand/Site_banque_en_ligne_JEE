package com.example.live.model.compteCourant;

import com.example.live.model.compteCourant.Comptecourant;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoperation", nullable = false)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comptecourant_idcomptecourant", nullable = false)

    private Comptecourant comptecourantIdcomptecourant;

    public Comptecourant getComptecourantIdcomptecourant() {
        return comptecourantIdcomptecourant;
    }

    public void setComptecourantIdcomptecourant(Comptecourant comptecourantIdcomptecourant) {
        this.comptecourantIdcomptecourant = comptecourantIdcomptecourant;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", montant=" + montant +
                ", date=" + date +
                ", comptecourantIdcomptecourant=" + comptecourantIdcomptecourant +
                '}';
    }
}