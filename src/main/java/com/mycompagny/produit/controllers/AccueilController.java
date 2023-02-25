package com.mycompagny.produit.controllers;

import com.mycompagny.produit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccueilController {


    @Autowired
    ProduitService produitService;

    @RequestMapping("/")
    public String accueil() {
        System.out.println("Affichage de la page d'accueil");
		return "home";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "home";
    }


}
