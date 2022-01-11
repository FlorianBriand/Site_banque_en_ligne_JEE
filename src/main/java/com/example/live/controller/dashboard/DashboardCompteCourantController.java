package com.example.live.controller.dashboard;

import com.example.live.dao.compteCourant.ComptecourantRepository;
import com.example.live.dao.compteCourant.OperationRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteCourant.Comptecourant;
import com.example.live.model.compteCourant.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardCompteCourantController {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    ComptecourantRepository comptecourantRepository;


    @GetMapping(path = "/dashboardCC")
    public String loadDashboardCompteCourantPage(Model model, HttpSession session) {

        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        List<Operation> operationList = recuperationListOperation(session);
        double sommeOperations = sommeOperations(operationList);

        modificationSommeCompteCourant(sommeOperations, session);

        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("sommeOperations", sommeOperations);
        model.addAttribute("operationList", operationList);

        return "dashboard/dashboardCC";
    }

    private void modificationSommeCompteCourant(double sommeOperations, HttpSession session) {
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");
        comptecourant.setMontant(sommeOperations);
        comptecourantRepository.save(comptecourant);
    }

    private List<Operation> recuperationListOperation(HttpSession session) {
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");
        return operationRepository.findOperationsByComptecourantIdcomptecourant(comptecourant);

    }

    private double sommeOperations(List<Operation> operationList) {
        double somme = 0;
        for (Operation operation : operationList) {
            somme = somme + operation.getMontant();
        }
        return somme;
    }


}
