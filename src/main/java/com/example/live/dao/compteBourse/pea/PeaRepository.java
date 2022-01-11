package com.example.live.dao.compteBourse.pea;

import com.example.live.model.Portefeuille;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.pea.Pea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeaRepository extends JpaRepository<Pea, Integer> {

    List<Portefeuille> findPeasByComptebourseIdcomptebourse(Comptebourse comptebourse);

    List<Pea> findByComptebourseIdcomptebourse(Comptebourse comptebourse);

    Pea findPeaById(int i);
}