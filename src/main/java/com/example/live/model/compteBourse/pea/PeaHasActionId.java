package com.example.live.model.compteBourse.pea;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PeaHasActionId implements Serializable {
    private static final long serialVersionUID = -260182043323186199L;
    @Column(name = "pea_idpea", nullable = false)
    private Integer peaIdpea;
    @Column(name = "action_idaction", nullable = false)
    private Integer actionIdaction;

    public PeaHasActionId(int actionIdaction, int peaIdpea) {
        this.actionIdaction = actionIdaction;
        this.peaIdpea = peaIdpea;
    }

    public PeaHasActionId() {

    }

    public Integer getActionIdaction() {
        return actionIdaction;
    }

    public void setActionIdaction(Integer actionIdaction) {
        this.actionIdaction = actionIdaction;
    }

    public Integer getPeaIdpea() {
        return peaIdpea;
    }

    public void setPeaIdpea(Integer peaIdpea) {
        this.peaIdpea = peaIdpea;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionIdaction, peaIdpea);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PeaHasActionId entity = (PeaHasActionId) o;
        return Objects.equals(this.actionIdaction, entity.actionIdaction) &&
                Objects.equals(this.peaIdpea, entity.peaIdpea);
    }

    @Override
    public String toString() {
        return "PeaHasActionId{" +
                "peaIdpea=" + peaIdpea +
                ", actionIdaction=" + actionIdaction +
                '}';
    }
}