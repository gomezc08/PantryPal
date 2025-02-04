package com.gomezc.pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.connector.Response;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    
    @Autowired
    private final ApiService apiService;

    @Autowired
    private ObjectMapper mapper;
    

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/nutrition")
    public String getNutritionData(@RequestParam String query) {
        return apiService.fetchNutritionData(query);
    }

    // takes in the scanned data and sends it to the api service to fetch branded nutrition data.
    // then adds to our db.
    @PostMapping("/scan")
    public void handleQrScan(@RequestBody Map<String, String> qrData) {    
        String data = qrData.get("qrData");
        log.info("Received QR Data: " + data);
        String s = apiService.fetchBrandedNutritionData(data);
        try {
            JsonNode node = mapper.readTree(s);
            log.info("Received Nutrition Data: " + node);
            Path path = Paths.get("src\\main\\java\\com\\gomezc\\pantrypal\\JsonFile.json");
            Files.write(path, s.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // parse json.
            String fName = node.get("foods").get(0).get("food_name").asText();
            log.info("Food Name: " + fName);

            String brandName = node.get("foods").get(0).get("brand_name").asText();
            log.info("Brand Name: " + brandName);

            // add to db.
            //apiService.addFoodItem(null);
        } 
        catch (Exception e) {
            log.error("Error parsing JSON: " + e);
        }
    }
}