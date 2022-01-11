package com.example.live.dao.compteCourant;

import com.example.live.model.compteCourant.Comptecourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptecourantRepository extends JpaRepository<Comptecourant, Integer> {
    Comptecourant findComptecourantById(int i);
}