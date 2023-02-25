package com.mycompagny.produit.controllers.web;

import com.mycompagny.produit.dto.ProduitDto;
import com.mycompagny.produit.entity.Produit;
import com.mycompagny.produit.service.ProduitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//je dis à Spring que cette classe va contenir des méthodes webservices consommé à travers un navigateur
@Controller
//permet de taper dans le navigateur l'url contenant localhost/produits/api (/produits est défini dans le fichier de propriété
@RequestMapping("/produit")
//je permet à tous les adresses origines de consommer ces Webservices
//@CrossOrigin
public class ProduitControllerWeb {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ModelMapper modelMapper;

    private ProduitDto convertToDto(Produit entity) {

        ProduitDto dto = modelMapper.map(entity, ProduitDto.class);
        return dto;
    }

    private Produit convertToEntity(ProduitDto dto)  {

        Produit entity = modelMapper.map(dto, Produit.class);
        return entity;
    }


    //http://localhost/produits/api/
    //METHODE 1 :
   /*@RequestMapping("/")
    public String displayHome(){
        System.out.println("API Rest : La méthode display home a été invoqué");
        return "home";
    }*/

    //METHODE 2: avec récupération de données et insertion dans le request
   /*@RequestMapping("/")
    public String displayHome(HttpServletRequest request){
        System.out.println("La méthode display home a été invoqué");
        List<Produit> produits = produitService.getAllProduits();
        request.setAttribute("produits", produits);
        return "home";
    }*/

    //METHODE 3 : avec récupération de données
    /*@RequestMapping("/")
    public List<Produit> displayHome(){
        System.out.println("La méthode display home a été invoqué");
        List<Produit> produits = produitService.getAllProduits();
        return produits;
    }*/

    //METHODE 4 : permet d'appeler la page produit-home.html
    // en ajoutant les objets utilisé dans la vue
    /*@RequestMapping("/produit-home")
    public ModelAndView listProduits(){
        System.out.println("La méthode display home a été invoqué");
        List<Produit > produits = produitService.getAllProduits();
        ModelAndView mv = new ModelAndView("produit-home");
        mv.addObject("produits",produits);
        return mv;
    }*/

    //METHODE 5 :
    @RequestMapping("/produitHome")
    public String listProduits(Model model) {
        System.out.println("La méthode display home a été invoqué");

        List<Produit> produits = produitService.getAllProduits();
        List<ProduitDto> produitsDto= produits.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        model.addAttribute("produitsDto",produitsDto);
        return "webProduit/produitHome";
    }

    @RequestMapping("/produitHomeApiRest")
    public String produitHomeApiRest(Model model) {
        System.out.println("La méthode produitHomeApiRest home a été invoqué");
        return "api/produitHome";
    }



    // affiche la liste des produits en fonction avec l'url : localhost/produits/api
    //retourne la liste des produits en format JSON
    /*@RequestMapping(method = RequestMethod.GET)
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }*/

    //Méthode 0 : le value sera ajouter dans l'url par ex : localhost/produits/api/1
    //permet de consulter le produit en fonction de l'id
    //retourne le produit en format JSON
    /*@GetMapping(value = "/{id}")
    public Produit getProduitById(@PathVariable("id") Long id) {
        return produitService.getProduit(id);
    }*/

    //Méthode2 : le value sera ajouter dans l'url par ex : localhost/produits/api/1
    //permet de consulter le produit en fonction de l'id
    /*@GetMapping(value = "/{id}")
    public ModelAndView getProduitById(@PathVariable("id") Long id) {
        System.out.println("La méthode getProduitById a été invoqué");
        ModelAndView mv = new ModelAndView("produit-detail");
        mv.addObject("produit",produitService.getProduit(id));
        return mv;
    }*/

    //Méthode3 : utilisation du ModelAndView
    // la value sera ajouter dans l'url par ex : localhost/produits/api/1
    //permet de consulter le produit en fonction de l'id
    /*@GetMapping(value = "/{id}")
    public ModelAndView getProduitById(@PathVariable("id") Long id, Model model) {
        System.out.println("La méthode getProduitById a été invoqué");
        ModelAndView mv = new ModelAndView("produit-detail");
        mv.addObject("produit",produitService.getProduit(id));
        return mv;
    }*/

    //Méthode4 : utilisation du Model
    @GetMapping(value = "/{id}")
    public String detailProduit(@PathVariable("id") Long id, Model model) {
        System.out.println("La méthode getProduitById a été invoqué");
        model.addAttribute("produitDto", convertToDto(produitService.getProduit(id)));
        return "webProduit/produitDetail";
    }

    // affiche le formulaire de création vide
    @GetMapping(value = "/produitCreateForm")
    public String displayProduitCreateForm(@ModelAttribute ProduitDto produitDto, Model model) {
        System.out.println("Appel de la méthode displayProduitCreateForm");
        produitDto.setDateCreation(new Date());
        model.addAttribute("produitDto", produitDto);
        return "webProduit/produitCreateForm";
    }
    //permet de faire un post et d'inserer une nouvelle ligne en base en envoyant les données en format JSON
    //@RequestMapping(method = RequestMethod.POST)
    /*@PostMapping(value = "/{id}")
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }*/

    //Methode 1 :
    // permet de faire un post et d'inserer une nouvelle ligne en base en envoyant les données en format JSON
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping(value = "/createProduit")
    public String createProduit(@Valid @ModelAttribute ProduitDto produitDto, BindingResult results, Model model ) {
        System.out.println("Appel de la méthode createProduit");
        System.out.println("Date Création" + produitDto.getDateCreation());
        if(results.hasErrors()){
            return "webProduit/produitCreateForm";
        }
        Produit produitCreated = produitService.saveProduit(convertToEntity(produitDto));
        model.addAttribute("produitDto", convertToDto(produitCreated));

        return "webProduit/produitCreated";
    }

    @GetMapping(value = "/showUpdateProduit/{id}")
    public String showUpdateProduit(@PathVariable("id") Long id, ModelMap model) {
        System.out.println("Appel de la méthode showUpdateProduit");
        System.out.println("Identifiant :"+id);
        model.addAttribute("produitDto", convertToDto(produitService.getProduit(id)));
        return "webProduit/produitUpdateForm";
    }

    //permet de modifier une donnée de l'objet passé en mode Json, attention faut mettre idProduit dans le JSon,
    //sinon une nouvelle ligne sera ajouter
    @PostMapping(value = "/updateProduit")
    public String updateProduit(@Valid @ModelAttribute ProduitDto produitDto, BindingResult results, Model model) {
        System.out.println("Appel de la méthode updateProduit");
        Produit produitUpdaded = produitService.updateProduit(convertToEntity(produitDto));
        model.addAttribute("produitDto", convertToDto(produitUpdaded));
        return "webProduit/produitUpdated";
    }

    //permet de supprimer une donnée par rapport à l'id. l'url à mettre est : localhost/produits/api/5
    @GetMapping(value = "/delete/{id}")
    public String deleteProduit(@PathVariable("id") Long id,Model model) {
        System.out.println("Appel de la méthode deleteProduit");
        produitService.deleteProduitById(id);
        return this.listProduits(model);
    }

    //affiche la liste des produits en fonction de la catégorie ex: localhost/produits/prodscat/5
    @RequestMapping(value = "/prodscat/{idCat}", method = RequestMethod.GET)
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
        return produitService.findByCategorieIdCat(idCat);
    }


}
