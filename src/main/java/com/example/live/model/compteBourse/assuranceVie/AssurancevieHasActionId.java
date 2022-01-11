package com.example.live.model.compteBourse.assuranceVie;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AssurancevieHasActionId implements Serializable {
    private static final long serialVersionUID = -1599227869081919947L;
    @Column(name = "assurancevie_idassurancevie", nullable = false)
    private Integer assurancevieIdassurancevie;
    @Column(name = "action_idaction", nullable = false)
    private Integer actionIdaction;

    public AssurancevieHasActionId(int actionIdaction,int assurancevieIdassurancevie) {
        this.actionIdaction = actionIdaction;
        this.assurancevieIdassurancevie = assurancevieIdassurancevie;
    }

    public AssurancevieHasActionId() {

    }

    public Integer getActionIdaction() {
        return actionIdaction;
    }

    public void setActionIdaction(Integer actionIdaction) {
        this.actionIdaction = actionIdaction;
    }

    public Integer getAssurancevieIdassurancevie() {
        return assurancevieIdassurancevie;
    }

    public void setAssurancevieIdassurancevie(Integer assurancevieIdassurancevie) {
        this.assurancevieIdassurancevie = assurancevieIdassurancevie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionIdaction, assurancevieIdassurancevie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AssurancevieHasActionId entity = (AssurancevieHasActionId) o;
        return Objects.equals(this.actionIdaction, entity.actionIdaction) &&
                Objects.equals(this.assurancevieIdassurancevie, entity.assurancevieIdassurancevie);
    }
}