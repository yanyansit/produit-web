package com.mycompagny.produit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDto implements Serializable {

	private static final long serialVersionUID = 8622079431204218869L;

	private Long idProduit;

	@NotNull
	@Size(min=1,max=60)
	private String nomProduit;

	@NotNull
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min(1)
	private Double prixProduit;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date dateCreation;

	private CategorieDto categorie;

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", prixProduit=" + prixProduit
				+ ", dateCreation=" + dateCreation + "]";
	}

	public CategorieDto getCategorie(){
		if(this.categorie==null){
			this.categorie = new CategorieDto();
		}
		return this.categorie;
	}


}
