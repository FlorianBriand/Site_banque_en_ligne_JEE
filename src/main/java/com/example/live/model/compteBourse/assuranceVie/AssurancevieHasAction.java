package com.example.live.model.compteBourse.assuranceVie;

import com.example.live.model.compteBourse.CompteBourseHasAction;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "assurancevie_has_action")
public class AssurancevieHasAction extends CompteBourseHasAction {
    @EmbeddedId
    private AssurancevieHasActionId id;

    @Column(name = "prixachat")
    private Double prixachat;

    @Column(name = "dateachat")
    private LocalDate dateachat;

    @Column(name = "quantite")
    private  int quantite;

    public LocalDate getDateachat() {
        return dateachat;
    }

    public void setDateachat(LocalDate dateachat) {
        this.dateachat = dateachat;
    }

    public Double getPrixachat() {
        return prixachat;
    }

    public void setPrixachat(Double prixachat) {
        this.prixachat = prixachat;
    }

    public AssurancevieHasActionId getId() {
        return id;
    }

    public void setId(AssurancevieHasActionId id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}