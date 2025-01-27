package com.gomezc.pantrypal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String getApiKey() {
        return apiKey;
    }
}