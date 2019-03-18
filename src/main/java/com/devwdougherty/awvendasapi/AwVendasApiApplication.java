package com.devwdougherty.awvendasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AwVendasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwVendasApiApplication.class, args);
	}
}
