package com.example.live.controller;


import com.example.live.dao.EnveloppeRepository;
import com.example.live.dao.UtilisateurRepository;
import com.example.live.dao.compteBourse.ComptebourseRepository;
import com.example.live.dao.compteCourant.ComptecourantRepository;
import com.example.live.model.Enveloppe;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteCourant.Comptecourant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    EnveloppeRepository enveloppeRepository;

    @Autowired
    ComptecourantRepository comptecourantRepository;

    @Autowired
    ComptebourseRepository comptebourseRepository;

    @PostMapping(path = "/connexion")
    public String connexion(Utilisateur utilisateur, HttpSession session) {
        System.out.println(utilisateur);

        boolean b = utilisateurRepository.existsUtilisateurByMdpEqualsAndAndNomAndAndPrenom(utilisateur.getMdp(), utilisateur.getNom(), utilisateur.getPrenom());
        System.out.println(b);
        if (b) {

            Utilisateur utilisateur1 = utilisateurRepository.findUtilisateurByMdpEqualsAndAndNomAndAndPrenom(utilisateur.getMdp(), utilisateur.getNom(), utilisateur.getPrenom());
            session.setAttribute("idUtilisateur", utilisateur1.getId());
            intialisationIds(utilisateur, session);

            return "succes/accesEspace";
        } else {
            return "connexion/login";
        }

    }


    private void intialisationIds(Utilisateur utilisateur, HttpSession session) {

        utilisateur  = utilisateurRepository.findUtilisateurByMdpEqualsAndAndNomAndAndPrenom(utilisateur.getMdp(), utilisateur.getNom(), utilisateur.getPrenom());
        System.out.println(utilisateur);

        Enveloppe enveloppe = enveloppeRepository.findEnveloppeById(utilisateur.getEnveloppeIdenveloppe().getId());

        Comptebourse comptebourse = enveloppe.getComptebourseIdcomptebourse();
        Comptecourant comptecourant = enveloppe.getComptecourantIdcomptecourant();

        session.setAttribute("Utilisateur", utilisateur);
        session.setAttribute("CompteCourant", comptecourant);
        session.setAttribute("CompteBourse", comptebourse);

        Utilisateur utilisateur1 = (Utilisateur) session.getAttribute("Utilisateur");
        System.out.println(utilisateur1);




    }

    @PostMapping(path = "/add-utilisateur")
    public String addUtilisateur(Utilisateur utilisateur) {
        System.out.println(utilisateur);

        Comptebourse comptebourse = comptebourseRepository.save(new Comptebourse());
        System.out.println(comptebourse);

        Comptecourant comptecourant = new Comptecourant();
        comptecourant.setMontant(0.0);
        comptecourant.setIntitule("Compte Courant");
        comptecourant = comptecourantRepository.save(comptecourant);
        System.out.println(comptecourant);

        Enveloppe enveloppe = new Enveloppe();
        enveloppe.setComptebourseIdcomptebourse(comptebourse);
        enveloppe.setComptecourantIdcomptecourant(comptecourant);

        enveloppe = enveloppeRepository.save(enveloppe);
        System.out.println(enveloppe);

        utilisateur.setEnveloppeIdenveloppe(enveloppe);
        System.out.println(utilisateur);

        utilisateurRepository.save(utilisateur);
        return "succes/inscriptionReussite";

    }


}
