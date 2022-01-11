package com.example.live.controller.compteBourse.action;

import com.example.live.dao.compteBourse.action.ActionRepository;
import com.example.live.dao.compteBourse.assuranceVie.AssurancevieRepository;
import com.example.live.dao.compteBourse.pea.PeaRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.action.Action;
import com.example.live.model.compteBourse.assuranceVie.Assurancevie;
import com.example.live.model.compteBourse.pea.Pea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ActionwidgetController {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    PeaRepository peaRepository;

    @Autowired
    AssurancevieRepository assurancevieRepository;

    @RequestMapping(value = "/actionwidget/{symbol}", method = GET)
    public String transfereSymbol(@PathVariable("symbol") String symbol, Model model, HttpSession session) {

        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        Action action = actionRepository.findActionBySymbol(symbol);
        String name = action.getName();

        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("name", name);
        model.addAttribute("symbol", symbol);
        model.addAttribute("verifPEA", verifiePEA(session));
        model.addAttribute("verifAssuranceVie", verifieAssuranceVie(session));
        return "action/actionwidget";
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
        List<Assurancevie> action= assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse);

        if (action.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

}
