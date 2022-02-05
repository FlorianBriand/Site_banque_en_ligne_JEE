package com.example.live.controller.dashboard;

import com.example.live.dao.EnveloppeRepository;
import com.example.live.dao.compteBourse.action.ActionRepository;
import com.example.live.dao.compteBourse.assuranceVie.AssurancevieRepository;
import com.example.live.dao.compteBourse.pea.PeaRepository;
import com.example.live.dao.compteCourant.OperationRepository;
import com.example.live.dao.historique.HistoriqueRepository;
import com.example.live.model.Portefeuille;
import com.example.live.model.Utilisateur;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.action.Action;
import com.example.live.model.compteCourant.Comptecourant;
import com.example.live.model.compteCourant.Operation;
import com.example.live.model.historique.Historique;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.OperatingSystemMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    EnveloppeRepository enveloppeRepository;

    @Autowired
    AssurancevieRepository assurancevieRepository;

    @Autowired
    PeaRepository peaRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    HistoriqueRepository historiqueRepository;

    private final String cleAPI = "204145db387b63389b38057af850e570";
    private final String listaction = "MSFT,AAPL,AMZN,GOOG,FB,INTC,CSCO,CMCSA,PEP,ADBE,NVDA,NFLX,PYPL,AMGN,AVGO";


    @GetMapping(path = "/dashboard")
    public String loadDashboardPage(Model model, HttpSession session) throws MalformedURLException {
        //initialisation variable de session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        List<Portefeuille> enveloppeList = recuperationListEnveloppe(session);

        //gestion de l'historique pour le graph
        initialisationHistorique(utilisateur, model, session);

        model.addAttribute("nomUtilisateur", utilisateur.getNom());
        model.addAttribute("prenomUtilisateur", utilisateur.getPrenom());
        model.addAttribute("enveloppeList", enveloppeList);
        model.addAttribute("sommeEnveloppe", sommeEnveloppes(enveloppeList));


        return "dashboard/dashboard";
    }

    private void initialisationHistorique(Utilisateur utilisateur, Model model, HttpSession session) {
        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");

        List<Operation> operationList = operationRepository.findOperationsByComptecourantIdcomptecourant(comptecourant);

        List<Historique> historiqueList = historiqueRepository.findAllByidutilisateur(utilisateur.getId());

        if (historiqueList.size() == 0) {
            Historique historique = new Historique();
            historique.setMontant(0.0);
            historique.setDate(LocalDate.now());
            historique.setIdutilisateur(utilisateur.getId());
            historiqueList.add(historique);
            historiqueRepository.save(historique);
        } else {
            double derniereMontant = historiqueList.get(historiqueList.size() - 1).getMontant();

            double sommeMontant = sommeOperations(operationList);

            //si montant change mettre dans bdd
            double difference = Math.abs(sommeMontant - derniereMontant);
            if (difference > 0.1) {
                //j'ajoute point dans graph
                LocalDate now = LocalDate.now();
                Historique historique = new Historique();
                historique.setDate(now);
                historique.setMontant(sommeMontant);
                historique.setIdutilisateur(utilisateur.getId());
                System.out.println(historique);
                historiqueRepository.save(historique);
                historiqueList.add(historique);
            }
        }

        model.addAttribute("historiqueList", historiqueList);
    }

    private List<Portefeuille> recuperationListEnveloppe(HttpSession session) throws MalformedURLException {

        Comptecourant comptecourant = (Comptecourant) session.getAttribute("CompteCourant");
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");

        List<Portefeuille> enveloppeList = new ArrayList<>();

        List<Portefeuille> enveloppeListAssuranceVie = assurancevieRepository.findAssuranceviesByComptebourseIdcomptebourse(comptebourse);
        List<Portefeuille> enveloppeListPea = peaRepository.findPeasByComptebourseIdcomptebourse(comptebourse);

        enveloppeList.add(comptecourant);
        enveloppeList.addAll(enveloppeListAssuranceVie);
        enveloppeList.addAll(enveloppeListPea);

        initialisationActionsBourse();

        return enveloppeList;
    }

    private void initialisationActionsBourse() throws MalformedURLException {
        URL url = new URL("https://financialmodelingprep.com/api/v3/quote/" + listaction + "?apikey=" + cleAPI);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {

            // Convertir un BufferedReader en un String
            String jsonInStringActions = reader.lines().collect(Collectors.joining());

            JSONArray tableauJson = new JSONArray(jsonInStringActions);

            //maj des prices
            for (int i = 1; i < 16; i++) {
                Action action = actionRepository.findActionById(i);

                action.setPrice((double) tableauJson.optJSONObject(i - 1).get("price"));

                //update bdd
                actionRepository.save(action);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private double sommeEnveloppes(List<Portefeuille> enveloppeList) {
        double somme = 0;
        for (Portefeuille portefeuille : enveloppeList) {
            somme = somme + portefeuille.getMontant();
        }
        return somme;
    }

    private double sommeOperations(List<Operation> operationList) {
        double somme = 0;
        for (Operation operation : operationList) {
            somme = somme + operation.getMontant();
        }
        return somme;
    }


}
