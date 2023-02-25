package com.mycompagny.produit.ConfigUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "com.exemple.webapp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomProperties {

	@Value("${apiUrl}")
	private String apiUrl;



}