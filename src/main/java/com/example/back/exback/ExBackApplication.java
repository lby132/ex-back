package com.example.back.exback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExBackApplication.class, args);
	}

}
