package com.alexdiru.primeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PrimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeServiceApplication.class, args);
	}

}
