package com.example.live.controller.dashboard;

import com.example.live.dao.compteBourse.action.ActionRepository;
import com.example.live.dao.compteBourse.action.PeaHasActionRepository;
import com.example.live.dao.compteBourse.pea.PeaRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.AffichageListCompteBourse;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.action.Action;
import com.example.live.model.compteBourse.pea.Pea;
import com.example.live.model.compteBourse.pea.PeaHasAction;
import com.example.live.model.compteBourse.pea.PeaHasActionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardPeaController {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    PeaHasActionRepository peaHasActionRepository;

    @Autowired
    PeaRepository peaRepository;


    @GetMapping(path = "/dashboardPEA")
    public String loadDashboardPeaPage(Model model, HttpSession session) {
        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        boolean verifPEA=verifiePEA(session);

        if (verifPEA==false) {

            List<AffichageListCompteBourse> affichageListCompteBourses = recuperationAffichageListCompteBourses(session);
            double sommePea = recuperationSommmePea(affichageListCompteBourses, session);

            double sommePlusValue = recuperationSommmePlusValue(affichageListCompteBourses, session);
            modificationSommePea(sommePea, session);

            model.addAttribute("sommePlusValue", sommePlusValue);
            model.addAttribute("nomUtilisateur", utilisateur.getNom());
            model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
            model.addAttribute("sommePea", sommePea);
            model.addAttribute("affichageListCompteBourses", affichageListCompteBourses);
            model.addAttribute("verifPEAaction", verifPEAaction(affichageListCompteBourses));
        }

        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("verifPEA", verifiePEA(session));

        return "dashboard/dashboardPEA";
    }

    private double recuperationSommmePlusValue(List<AffichageListCompteBourse> affichageListCompteBourses, HttpSession session) {
        double sommePlusValue = 0;
        for(AffichageListCompteBourse affichageListCompteBourse : affichageListCompteBourses){
            sommePlusValue = sommePlusValue+ affichageListCompteBourse.getCompteBourseHasAction().getQuantite()*(affichageListCompteBourse.getAction().getPrice()-affichageListCompteBourse.getCompteBourseHasAction().getPrixachat());
        }
        return sommePlusValue;
    }

    private boolean verifPEAaction(List<AffichageListCompteBourse> affichageListCompteBourses){
        if(affichageListCompteBourses.isEmpty()){
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

    private void modificationSommePea(double sommePea, HttpSession session) {
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        Pea pea = peaRepository.findByComptebourseIdcomptebourse(comptebourse).get(0);
        pea.setMontant(sommePea);
        peaRepository.save(pea);
    }

    private double recuperationSommmePea(List<AffichageListCompteBourse> affichageListCompteBourses, HttpSession session) {
        double somme = 0;
        for (AffichageListCompteBourse affichageListCompteBourse:affichageListCompteBourses){
            int quantite = affichageListCompteBourse.getCompteBourseHasAction().getQuantite();
            double prix = affichageListCompteBourse.getAction().getPrice();
            somme=somme+quantite*prix;
        }
        return somme;
    }

    private List<AffichageListCompteBourse> recuperationAffichageListCompteBourses(HttpSession session) {
        List<PeaHasAction> peaHasActionList = recuperationListPeaHasAction(session);

        List<Action> actionList = actionRepository.findAll();

        List<AffichageListCompteBourse> affichageListCompteBourses = new ArrayList<>();

        for (PeaHasAction peaHasAction : peaHasActionList) {
            int idAction = peaHasAction.getId().getActionIdaction();
            Action action = actionList.get(idAction - 1);
            AffichageListCompteBourse affichageListCompteBourse = new AffichageListCompteBourse(action, peaHasAction);
            affichageListCompteBourses.add(affichageListCompteBourse);
        }
        return affichageListCompteBourses;
    }

    private List<PeaHasAction> recuperationListPeaHasAction(HttpSession session) {
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");

        List<Pea> peaList = peaRepository.findByComptebourseIdcomptebourse(comptebourse);

        int idPea = peaList.get(0).getId();

        List<PeaHasAction> peaHasActionList = new ArrayList<>();

        for (int i = 1; i < actionRepository.findAll().size() + 1; i++) {
            PeaHasActionId peaHasActionId = new PeaHasActionId(i, idPea);
            if (peaHasActionRepository.existsPeaHasActionById(peaHasActionId)) {
                peaHasActionList.add(peaHasActionRepository.findPeaHasActionById(peaHasActionId));
            }

        }

        return peaHasActionList;
    }


}
