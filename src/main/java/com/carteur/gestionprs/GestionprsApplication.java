package com.carteur.gestionprs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class GestionprsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionprsApplication.class, args);
	}    
}
