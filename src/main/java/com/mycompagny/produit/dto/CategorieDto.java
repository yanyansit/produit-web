package com.mycompagny.produit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompagny.produit.entity.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {

    private Long idCat;

    private String nomCat;

    private String descriptionCat;

    private List<Produit> produits;
}
