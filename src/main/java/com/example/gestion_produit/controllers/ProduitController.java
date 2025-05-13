package com.example.gestion_produit.controllers;

import com.example.gestion_produit.entities.Produit;
import com.example.gestion_produit.repositoriy.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitRepository repo;

    @GetMapping
    public String listProduits(Model model,
                               @RequestParam(name="mc", defaultValue="") String mc) {
        model.addAttribute("listeProduits",
            repo.findByDesignationContains(mc));
        model.addAttribute("mc", mc);
        return "produits";
    }

    @GetMapping("/nouveau")
    public String formProduit(Model model) {
        model.addAttribute("produit", new Produit());
        return "formProduit";
    }

    @PostMapping("/save")
    public String saveProduit(@ModelAttribute Produit produit) {
        repo.save(produit);
        return "redirect:/produits";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteProduit(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/produits";
    }

    @GetMapping("/edit/{id}")
    public String editProduit(@PathVariable Long id, Model model) {
        Produit p = repo.findById(id).orElseThrow();
        model.addAttribute("produit", p);
        return "formProduit";
    }
}