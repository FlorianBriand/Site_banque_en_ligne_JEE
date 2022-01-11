package com.example.live.controller.compteBourse.pea;

import com.example.live.dao.compteBourse.ComptebourseRepository;
import com.example.live.dao.compteBourse.pea.PeaRepository;
import com.example.live.model.compteBourse.Comptebourse;
import com.example.live.model.compteBourse.pea.Pea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PeaController {

    @Autowired
    PeaRepository peaRepository;

    @Autowired
    ComptebourseRepository ComptebourseRepository;

    @GetMapping(path = "/add-Pea")
    public String loadPEAForm() {
        return "PEA/formPEA";
    }

    @PostMapping(path = "/add-Pea")
    public String addPEA(Pea pea, HttpSession session) {
        pea.setMontant(0.0);

        //Recuperation du comptebourse
        Comptebourse comptebourse = (Comptebourse) session.getAttribute("CompteBourse");

        System.out.println(comptebourse);
        pea.setComptebourseIdcomptebourse(comptebourse);
        peaRepository.save(pea);

        return "succes/succesAjoutPEA";
    }


}