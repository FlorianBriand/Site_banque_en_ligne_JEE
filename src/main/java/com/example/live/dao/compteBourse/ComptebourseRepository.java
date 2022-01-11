package com.example.live.dao.compteBourse;

import com.example.live.model.compteBourse.Comptebourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptebourseRepository extends JpaRepository<Comptebourse, Integer> {
    Comptebourse findComptebourseById(int i);
}