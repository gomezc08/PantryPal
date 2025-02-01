package com.gomezc.pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.catalina.connector.Response;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    
    @Autowired
    private final ApiService apiService;
    

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
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
        System.out.println(s);
    }

    // handle qr scanned data (look it up using api using fetchBrandedNutritionData).
    // @GetMapping("/scan")
    // public String getBrandedNutritionData(@RequestParam String query) {
    //     return apiService.fetchBrandedNutritionData(query);
    // }
}