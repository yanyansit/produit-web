package com.mycompagny.produit;

import com.mycompagny.produit.ConfigUtils.CustomProperties;
import com.mycompagny.produit.entity.Produit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
//@EnableJpaRepositories(enableDefaultTransactions = false)
//Par défaut Spring Data REST ne retourne pas la propriéte ID. Or on peut avoir
//besoin de l’ID dans le résultat JSON si on utilise des frontend tels que Angular ou
//ReactJS. Pour retourner l’ID, on doit faire la configuration suivante:
//ajouter CommandLineRunner,l'objet RepositoryRestConfiguration et la méthode run
public class ProduitApplication  implements CommandLineRunner {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	@Autowired
	private CustomProperties properties;

	public static void main(String[] args) {
		SpringApplication.run(ProduitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//exposeIdsFor permet de retourner l'Id du produit
		repositoryRestConfiguration.exposeIdsFor(Produit.class);
		System.out.println(properties.getApiUrl());
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
