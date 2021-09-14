package com.poslovnaInformatika.podsistemProdaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages= {"com.poslovnaInformatika.podsistemProdaje.model", "com.poslovnaInformatika.podsistemProdaje.controller", "com.poslovnaInformatika.podsistemProdaje.service", "com.poslovnaInformatika.podsistemProdaje.repository", "com.poslovnaInformatika.podsistemProdaje.dto"})
//
//@EntityScan(basePackages = {"com.poslovnaInformatika.podsistemProdaje.model"})
//@EnableJpaRepositories("com.poslovnaInformatika.podsistemProdaje.repository")
public class PodsistemProdajeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PodsistemProdajeApplication.class, args);
	}

}
