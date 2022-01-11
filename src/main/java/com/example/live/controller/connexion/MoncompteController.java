package com.example.live.controller.connexion;

import com.example.live.dao.compteBourse.assuranceVie.AssurancevieRepository;
import com.example.live.dao.compteBourse.pea.PeaRepository;
import com.example.live.dao.compteCourant.ComptecourantRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.assuranceVie.Assurancevie;
import com.example.live.model.compteBourse.pea.Pea;
import com.example.live.model.compteCourant.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MoncompteController {

    @Autowired
    PeaRepository peaRepository;

    @Autowired
    AssurancevieRepository assurancevieRepository;

    @GetMapping(path = "/moncompte")
    public String loadMoncomptePage(Model model, HttpSession session) {

        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        //envoie de d'utilisateur
        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("mdpUtilisateur", utilisateur.getMdp());

        System.out.println(verifieAssuranceViePEA(session));
        System.out.println(verifiePEA(session));
        System.out.println(verifieAssuranceVie(session));
        //verifie si pea et assurance vie existe
        model.addAttribute("verifAssuranceViePEA", verifieAssuranceViePEA(session));
        model.addAttribute("verifPEA", verifiePEA(session));
        model.addAttribute("verifAssuranceVie", verifieAssuranceVie(session));

        return "connexion/moncompte";
    }

    private boolean verifieAssuranceViePEA(HttpSession session){
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        List<Pea> action= peaRepository.findByComptebourseIdcomptebourse(comptebourse);
        List<Assurancevie> assurancevie= assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse);

        if (action.isEmpty() || assurancevie.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean verifiePEA(HttpSession session){
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        List<Pea> action= peaRepository.findByComptebourseIdcomptebourse(comptebourse);

        if (action.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean verifieAssuranceVie(HttpSession session){
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        List<Assurancevie> assurancevie= assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse);
        System.out.println(assurancevie);
        if (assurancevie.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
