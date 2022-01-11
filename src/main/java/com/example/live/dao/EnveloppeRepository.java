package com.example.live.dao;

import com.example.live.model.Enveloppe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnveloppeRepository extends JpaRepository<Enveloppe, Integer> {
    Enveloppe findEnveloppeById(int i);
}