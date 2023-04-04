package com.theduckfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.theduckfood.repositories")
@EntityScan(basePackages = "com.theduckfood.entity")
@ComponentScan("com.theduckfood.*")
public class TheDuckFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheDuckFoodApplication.class, args);
	}

}
