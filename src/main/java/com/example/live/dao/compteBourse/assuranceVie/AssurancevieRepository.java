package com.example.live.dao.compteBourse.assuranceVie;

import com.example.live.model.Portefeuille;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.assuranceVie.Assurancevie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssurancevieRepository extends JpaRepository<Assurancevie, Integer> {

    List<Portefeuille> findAssuranceviesByComptebourseIdcomptebourse(Comptebourse comptebourse);
    List<Assurancevie> findByComptebourseIdcomptebourse(Comptebourse comptebourse);

    Assurancevie findAssurancevieById(int i);
}