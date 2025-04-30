package com.gomezc.pantrypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class PantrypalApplication {

	private static final Logger log = LoggerFactory.getLogger(PantrypalApplication.class);
	public static void main(String[] args) {
		// Load environment variables.
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );

		// logging environment variables
		log.info("API_KEY: {}", System.getProperty("API_KEY"));
		log.info("API_ID: {}", System.getProperty("API_ID"));
		log.info("DB_URL: {}", System.getProperty("DB_URL"));
		log.info("DB_USERNAME: {}", System.getProperty("DB_USERNAME"));
		log.info("DB_PASSWORD: {}", System.getProperty("DB_PASSWORD"));

		// run the application
		SpringApplication.run(PantrypalApplication.class, args);
		log.info("Hello! Initialized PantrypalApplication");
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
