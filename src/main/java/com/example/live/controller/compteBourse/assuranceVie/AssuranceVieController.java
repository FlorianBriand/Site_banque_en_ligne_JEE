package com.example.live.controller.compteBourse.assuranceVie;

import com.example.live.dao.compteBourse.ComptebourseRepository;
import com.example.live.dao.compteBourse.assuranceVie.AssurancevieRepository;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.assuranceVie.Assurancevie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AssuranceVieController {

    @Autowired
    AssurancevieRepository assurancevieRepository;
    @Autowired
    ComptebourseRepository comptebourseRepository;

    @GetMapping(path = "/add-AssuranceVie")
    public String loadAssuranceVieForm() {
        return "assuranceVie/formAssuranceVie";
    }

    @PostMapping(path = "/add-AssuranceVie")
    public String addAssuranceVie(Assurancevie assurancevie, HttpSession session) {
        assurancevie.setMontant(0.0);

        //Recuperation du comptebourse
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");

        assurancevie.setComptebourseIdcomptebourse(comptebourse);
        System.out.println(assurancevie);
        assurancevieRepository.save(assurancevie);
        return "succes/succesAjoutAV";
    }


}