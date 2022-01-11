package com.example.live.model.compteBourse;

import javax.persistence.*;

@Entity
@Table(name = "comptebourse")
public class Comptebourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomptebourse", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comptebourse{" +
                "id=" + id +
                '}';
    }
}