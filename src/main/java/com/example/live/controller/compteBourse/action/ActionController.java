package com.example.live.controller.compteBourse.action;

import com.example.live.dao.compteBourse.action.ActionRepository;
import com.example.live.dao.compteBourse.action.AvHasActionRepository;
import com.example.live.dao.compteBourse.action.PeaHasActionRepository;
import com.example.live.dao.compteBourse.assuranceVie.AssurancevieRepository;
import com.example.live.dao.compteBourse.pea.PeaRepository;
import com.example.live.dao.compteCourant.OperationRepository;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.AffichageListCompteBourse;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.action.Action;
import com.example.live.model.compteBourse.assuranceVie.Assurancevie;
import com.example.live.model.compteBourse.assuranceVie.AssurancevieHasAction;
import com.example.live.model.compteBourse.assuranceVie.AssurancevieHasActionId;
import com.example.live.model.compteBourse.pea.Pea;
import com.example.live.model.compteBourse.pea.PeaHasAction;
import com.example.live.model.compteBourse.pea.PeaHasActionId;
import com.example.live.model.compteCourant.Comptecourant;
import com.example.live.model.compteCourant.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ActionController {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    PeaRepository peaRepository;

    @Autowired
    PeaHasActionRepository peaHasActionRepository;

    @Autowired
    AssurancevieRepository assurancevieRepository;

    @Autowired
    AvHasActionRepository avHasActionRepository;

    @Autowired
    OperationRepository operationRepository;

    @RequestMapping(value = "/vendActionPEA/{idAction}", method = GET)
    public String vendActionPEA(@PathVariable("idAction") int idAction, HttpSession session) {

        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");

        List<Pea> peaList = peaRepository.findByComptebourseIdcomptebourse(comptebourse);
        int idPea = peaList.get(0).getId();

        PeaHasAction peaHasAction = peaHasActionRepository.findPeaHasActionById(new PeaHasActionId(idAction, idPea));

        Action action = actionRepository.findActionById(idAction);

        Operation operation = new Operation();
        operation.setDescription("Vente d'action " + action.getName());
        operation.setMontant(action.getPrice()*peaHasAction.getQuantite());
        operation.setComptecourantIdcomptecourant(comptecourant);
        operation.setDate(LocalDate.now());

        operationRepository.save(operation);

        peaHasActionRepository.deleteById(peaHasAction.getId());
        return "succes/succesAjoutActionPEA";
    }

    @RequestMapping(value = "/vendActionAV/{idAction}", method = GET)
    public String vendActionAV(@PathVariable("idAction") int idAction, HttpSession session) {

        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");

        List<Assurancevie> assurancevieList = assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse);
        int idAV = assurancevieList.get(0).getId();

        AssurancevieHasAction assurancevieHasAction = avHasActionRepository.findAssurancevieHasActionById(new AssurancevieHasActionId(idAction, idAV));

        Action action = actionRepository.findActionById(idAction);

        Operation operation = new Operation();
        operation.setDescription("Vente d'action " + action.getName());
        operation.setMontant(action.getPrice()*assurancevieHasAction.getQuantite());
        operation.setComptecourantIdcomptecourant(comptecourant);
        operation.setDate(LocalDate.now());

        operationRepository.save(operation);

        avHasActionRepository.deleteById(assurancevieHasAction.getId());
        return "succes/succesAjoutActionAV";
    }

    @PostMapping(path = "/add-actionPea/{symbol}")
    public String addActionPEA(@PathVariable("symbol") String symbol, PeaHasAction peaHasAction, HttpSession session) {

        peaHasAction.setDateachat(LocalDate.now());

        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");

        Pea pea = peaRepository.findByComptebourseIdcomptebourse(comptebourse).get(0);
        Action action = actionRepository.findActionBySymbol(symbol);

        PeaHasActionId peaHasActionId = new PeaHasActionId(action.getId(), pea.getId());

        peaHasAction.setPrixachat(action.getPrice());
        peaHasAction.setId(peaHasActionId);

        Operation operation = new Operation();
        operation.setComptecourantIdcomptecourant(comptecourant);
        operation.setDate(LocalDate.now());
        operation.setDescription("Achat action Pea" + action.getName());
        operation.setMontant(-1 * action.getPrice() * peaHasAction.getQuantite());

        operationRepository.save(operation);

        peaHasActionRepository.save(peaHasAction);

        return "succes/succesAjoutActionPEA";
    }


    @PostMapping(path = "/add-actionAV/{symbol}")
    public String addActionAV(@PathVariable("symbol") String symbol, AssurancevieHasAction assurancevieHasAction, HttpSession session) {

        assurancevieHasAction.setDateachat(LocalDate.now());

        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");

        Assurancevie assurancevie = assurancevieRepository.findByComptebourseIdcomptebourse(comptebourse).get(0);
        Action action = actionRepository.findActionBySymbol(symbol);

        AssurancevieHasActionId assurancevieHasActionId = new AssurancevieHasActionId(action.getId(),assurancevie.getId());

        assurancevieHasAction.setPrixachat(action.getPrice());
        assurancevieHasAction.setId(assurancevieHasActionId);

        Operation operation = new Operation();
        operation.setComptecourantIdcomptecourant(comptecourant);
        operation.setDate(LocalDate.now());
        operation.setDescription("Achat action Assurance vie" + action.getName());
        operation.setMontant(-1*action.getPrice()*assurancevieHasAction.getQuantite());

        operationRepository.save(operation);

        avHasActionRepository.save(assurancevieHasAction);

        return "succes/succesAjoutActionAV";
    }

    @GetMapping(path = "/action")
    public String loadactionPage(HttpSession session, Model model) {

        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        List<Action> touteslesactions = actionRepository.findAll();

        // Récupération des ids

        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("listAction", touteslesactions);
        return "action/action";
    }


}
