package com.mycompagny.produit.controllers.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mycompagny.produit.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mycompagny.produit.service.ProduitService;

//@Controller
//permet de taper dans le navigateur l'url contenant localhost/produits/api (/produits est défini dans le fichier de propriété
@RequestMapping("/web")
public class ProduitControllerWebOld {


    @Autowired
    ProduitService produitService;

    @RequestMapping("/afficherAccueil")
    public String afficherAccueil() {
        System.out.println("Web : La méthode afficherAccueil a été invoqué");
		return "home";
    }

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createProduit";
    }

    @RequestMapping("/saveProduit")
    public String saveProduit(@ModelAttribute("produit") Produit produit,
                              @RequestParam("date") String date,
                              ModelMap modelMap) throws ParseException {
        //conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        produit.setDateCreation(dateCreation);

        Produit saveProduit = produitService.saveProduit(produit);
        String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
        modelMap.addAttribute("msg", msg);
        return "createProduit";
    }

    @RequestMapping("/ListeProduits")
    public String listeProduits(ModelMap modelMap) {
        List<Produit> prods = produitService.getAllProduits();
        modelMap.addAttribute("produits", prods);
        return "listeProduits";
    }

    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(@RequestParam("id") Long id,
                                   ModelMap modelMap) {
        produitService.deleteProduitById(id);
        List<Produit> prods = produitService.getAllProduits();
        modelMap.addAttribute("produits", prods);
        return "listeProduits";
    }

    @RequestMapping("/modifierProduit")
    public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap) {
        Produit p = produitService.getProduit(id);
        modelMap.addAttribute("produit", p);
        return "editerProduit";
    }

    @RequestMapping("/updateProduit")
    public String updateProduit(@ModelAttribute("produit") Produit produit,
                                //@RequestParam("date") String date,
                                ModelMap modelMap) throws ParseException {
        //conversion de la date
        //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        //Date dateCreation = dateformat.parse(String.valueOf(date));
        //produit.setDateCreation(dateCreation);
        produitService.updateProduit(produit);
        List<Produit> prods = produitService.getAllProduits();
        modelMap.addAttribute("produits", prods);
        return "listeProduits";
    }


}
