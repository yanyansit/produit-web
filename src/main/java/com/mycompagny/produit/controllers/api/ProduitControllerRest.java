package com.mycompagny.produit.controllers.api;

import com.mycompagny.produit.entity.Produit;
import com.mycompagny.produit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//je dis à Spring que cette classe va contenir des méthodes webservices consommé à travers un navigateur
@RestController
//permet de taper dans le navigateur l'url contenant localhost/produits/api (/produits est défini dans le fichier de propriété
@RequestMapping("/api")
//je permet à tous les adresses origines de consommer ces Webservices
@CrossOrigin
public class ProduitControllerRest {

    @Autowired
    ProduitService produitService;

    //affiche la liste des produits en fonction avec l'url : localhost/produits/api
    @GetMapping
    public  List<Produit> getAllProduits() {
        System.out.println("API Rest getAllProduits");
        return produitService.getAllProduits();
    }

    //le value sera ajouter dans l'url par ex : localhost/produits/api/1
    //permet de consulter le produit en fonction de l'id
    @GetMapping(value = "/{id}")
    public  Produit getProduitById(@PathVariable("id") Long id) {
        System.out.println("API Rest getProduitById");
        return produitService.getProduit(id);
    }

    //permet de faire un post et d'inserer une nouvelle ligne en base en envoyant les données en format JSON
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        if(produit.getDateCreation()==null){
            produit.setDateCreation(new Date());
        }

        return produitService.saveProduit(produit);
    }

    //permet de modifier une donnée de l'objet passé en mode Json, attention faut mettre idProduit dans le JSon,
    //sinon une nouvelle ligne sera ajouter
    @PutMapping()
    public Produit updateProduit(@RequestBody Produit produit) {
        return produitService.updateProduit(produit);
    }

    //permet de supprimer une donnée par rapport à l'id. l'url à mettre est : localhost/produits/api/5
    @DeleteMapping(value = "/{id}")
    public void deleteProduit(@PathVariable("id") Long id) {
        produitService.deleteProduitById(id);
    }

    //affiche la liste des produits en fonction de la catégorie ex: localhost/produits/prodscat/5
    @RequestMapping(value = "/prodscat/{idCat}", method = RequestMethod.GET)
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
        return produitService.findByCategorieIdCat(idCat);
    }


}
