package com.example.live.controller.compteCourant;

import com.example.live.dao.compteCourant.OperationRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteCourant.Comptecourant;
import com.example.live.model.compteCourant.Operation;
import javassist.CtBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class OperationController {

    @Autowired
    OperationRepository operationRepository;

    @GetMapping(path = "/add-operation")
    public String loadOperationForm() {
        return "operation/formOperation";
    }

    @PostMapping(path = "/add-operation")
    public String addOperationToDatabase(Operation operation, HttpSession session) {
        // Récupération des ids
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        int idutilisateur = utilisateur.getId();

        Comptecourant compteCourant = new Comptecourant();
        compteCourant.setId(idutilisateur);
        operation.setComptecourantIdcomptecourant(compteCourant);
        operationRepository.save(operation);

        return "succes/succesAjoutOperation";
    }

}