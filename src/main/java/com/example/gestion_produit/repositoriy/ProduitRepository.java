package com.example.gestion_produit.repositoriy;

import com.example.gestion_produit.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByDesignationContains(String mc);
}