package com.gomezc.pantrypal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    @Value("${app.id}")
    private String appId;

    @Value("${app.key}")
    private String appKey;

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchNutritionData(String query) {
        try {
            // Nutritionix API endpoint
            String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-app-id", appId);
            headers.set("x-app-key", appKey);

            // Request body
            String requestBody = "{\"query\": \"" + query + "\"}";

            // Create the HTTP entity with headers and body
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make the POST request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            // Log the response
            logger.info("Nutritionix API response: {}", response.getBody());

            // Return the response body
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error fetching nutrition data: {}", e.getMessage());
            return "Error: " + e.getMessage(); // Return a user-friendly error message
        }
    }
}