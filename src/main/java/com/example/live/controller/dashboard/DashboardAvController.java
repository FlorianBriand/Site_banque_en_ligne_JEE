package com.example.live.controller.dashboard;

import com.example.live.dao.compteBourse.action.ActionRepository;
import com.example.live.dao.compteBourse.action.AvHasActionRepository;
import com.example.live.dao.compteBourse.assuranceVie.AssurancevieRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.AffichageListCompteBourse;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.action.Action;
import com.example.live.model.compteBourse.assuranceVie.Assurancevie;
import com.example.live.model.compteBourse.assuranceVie.AssurancevieHasAction;
import com.example.live.model.compteBourse.assuranceVie.AssurancevieHasActionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardAvController {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    AvHasActionRepository avHasActionRepository;

    @Autowired
    AssurancevieRepository assurancevieRepository;


    @GetMapping(path = "/dashboardAV")
    public String loadDashboardAVPage(Model model, HttpSession session) {
        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        boolean verifAssuranceVie = verifieAssuranceVie(session);

        if (verifAssuranceVie==false) {

            List<AffichageListCompteBourse> affichageListCompteBourses = recuperationAffichageListCompteBourses(session);
            double sommeAV = recuperationSommmeAV(affichageListCompteBourses, session);

            double sommePlusValue = recuperationSommmePlusValue(affichageListCompteBourses, session);
            modificationSommeAV(sommeAV, session);

            model.addAttribute("sommePlusValue", sommePlusValue);
            model.addAttribute("nomUtilisateur", utilisateur.getNom());
            model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
            model.addAttribute("sommeAV", sommeAV);
            model.addAttribute("affichageListCompteBourses", affichageListCompteBourses);
            model.addAttribute("verifAvAction", verifAssuranceVieaction(affichageListCompteBourses));

        }

        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("verifAssuranceVie", verifieAssuranceVie(session));

        return "dashboard/dashboardAV";
    }

    private double recuperationSommmePlusValue(List<AffichageListCompteBourse> affichageListCompteBourses, HttpSession session) {
        double sommePlusValue = 0;
        for(AffichageListCompteBourse affichageListCompteBourse : affichageListCompteBourses){
            sommePlusValue = sommePlusValue+ affichageListCompteBourse.getCompteBourseHasAction().getQuantite()*(affichageListCompteBourse.getAction().getPrice()-affichageListCompteBourse.getCompteBourseHasAction().getPrixachat());
        }
        return sommePlusValue;
    }

    private boolean verifAssuranceVieaction(List<AffichageListCompteBourse> affichageListCompteBourses){
        if(affichageListCompteBourses.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean verifieAssuranceVie(HttpSession session){
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        List<Assurancevie> action= assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse);

        if (action.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    private void modificationSommeAV(double sommeAV, HttpSession session) {
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        Assurancevie assurancevie = assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse).get(0);
        assurancevie.setMontant(sommeAV);
        assurancevieRepository.save(assurancevie);
    }

    private double recuperationSommmeAV(List<AffichageListCompteBourse> affichageListCompteBourses, HttpSession session) {
        double somme = 0;
        for (AffichageListCompteBourse affichageListCompteBourse:affichageListCompteBourses){
            int quantite = affichageListCompteBourse.getCompteBourseHasAction().getQuantite();
            double prix = affichageListCompteBourse.getAction().getPrice();
            somme=somme+quantite*prix;
        }
        return somme;
    }

    private List<AffichageListCompteBourse> recuperationAffichageListCompteBourses(HttpSession session) {
        List<AssurancevieHasAction> assurancevieHasActionList = recuperationListAVHasAction(session);

        List<Action> actionList = actionRepository.findAll();

        List<AffichageListCompteBourse> affichageListCompteBourses = new ArrayList<>();

        for (AssurancevieHasAction assurancevieHasAction: assurancevieHasActionList) {
            int idAction = assurancevieHasAction.getId().getActionIdaction();
            Action action = actionList.get(idAction - 1);
            AffichageListCompteBourse affichageListCompteBourse = new AffichageListCompteBourse(action, assurancevieHasAction);
            affichageListCompteBourses.add(affichageListCompteBourse);
        }
        return affichageListCompteBourses;
    }

    private List<AssurancevieHasAction> recuperationListAVHasAction(HttpSession session) {
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");

        List<Assurancevie> assurancevieList = assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse);

        int idAV = assurancevieList.get(0).getId();

        List<AssurancevieHasAction> assurancevieHasActionList = new ArrayList<>();

        for (int i = 1; i < actionRepository.findAll().size() + 1; i++) {
            AssurancevieHasActionId assurancevieHasActionId = new AssurancevieHasActionId(i, idAV);
            if (avHasActionRepository.existsAssurancevieHasActionById(assurancevieHasActionId)) {
                assurancevieHasActionList.add(avHasActionRepository.findAssurancevieHasActionById(assurancevieHasActionId));
            }

        }

        return assurancevieHasActionList;
    }
}
