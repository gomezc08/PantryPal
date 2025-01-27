package com.gomezc.pantrypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class PantrypalApplication {

	private static final Logger log = LoggerFactory.getLogger(PantrypalApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PantrypalApplication.class, args);
		log.info("Hello! Initialized PantrypalApplication");
	}

}
