package com.sillock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SillockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SillockApplication.class, args);
	}

}
