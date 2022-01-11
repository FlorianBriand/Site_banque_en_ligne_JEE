package com.example.live.model;

import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteCourant.Comptecourant;

import javax.persistence.*;

@Entity
@Table(name = "enveloppe")
public class Enveloppe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenveloppe", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comptecourant_idcomptecourant", nullable = false)
    private Comptecourant comptecourantIdcomptecourant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comptebourse_idcomptebourse", nullable = false)
    private Comptebourse comptebourseIdcomptebourse;

    public Comptebourse getComptebourseIdcomptebourse() {
        return comptebourseIdcomptebourse;
    }

    public void setComptebourseIdcomptebourse(Comptebourse comptebourseIdcomptebourse) {
        this.comptebourseIdcomptebourse = comptebourseIdcomptebourse;
    }

    public Comptecourant getComptecourantIdcomptecourant() {
        return comptecourantIdcomptecourant;
    }

    public void setComptecourantIdcomptecourant(Comptecourant comptecourantIdcomptecourant) {
        this.comptecourantIdcomptecourant = comptecourantIdcomptecourant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Enveloppe{" +
                "id=" + id +
                ", comptecourantIdcomptecourant=" + comptecourantIdcomptecourant +
                ", comptebourseIdcomptebourse=" + comptebourseIdcomptebourse +
                '}';
    }
}