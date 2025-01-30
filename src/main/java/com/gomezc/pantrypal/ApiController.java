package com.gomezc.pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // qrData : decodeText
    @PostMapping("/scan")
    public void handleQrScan(@RequestBody Map<String, String> qrData) {        
        String data = qrData.get("qrData");
        log.info("Received QR Data: " + data);
        // Process the data or log it as required
        //return ResponseEntity.ok("Received: " + data);
    }
}