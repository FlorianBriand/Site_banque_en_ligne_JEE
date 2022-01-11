package com.example.live.dao;

import com.example.live.model.Enveloppe;
import com.example.live.model.Portefeuille;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteCourant.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    List<Utilisateur> findUtilisateurByEnveloppeIdenveloppe(Enveloppe enveloppe);

    boolean existsUtilisateurByMdpEqualsAndAndNomAndAndPrenom(String mdp, String nom, String prenom);

    Utilisateur findUtilisateurByMdpEqualsAndAndNomAndAndPrenom(String mdp, String nom, String prenom);

}