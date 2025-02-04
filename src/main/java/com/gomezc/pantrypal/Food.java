package com.gomezc.pantrypal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Food {
    @Id
    @NotEmpty
    private String fName;  
    
    @NotEmpty
    private String fBrand;
    private Integer fCalories;
    private Integer fFat;
    private Integer fCarbs;
    private Integer fProtein;
    private Integer fSodium;
    private Integer fSugar;
    private Integer fPotassium;

    // Default constructor required by JPA
    public Food() {}

    // Constructor with all fields
    public Food(String fName, String fBrand, Integer fCalories, Integer fFat, 
                Integer fCarbs, Integer fProtein, Integer fSodium, Integer fSugar, 
                Integer fPotassium) {
        this.fName = fName;
        this.fBrand = fBrand;
        this.fCalories = fCalories;
        this.fFat = fFat;
        this.fCarbs = fCarbs;
        this.fProtein = fProtein;
        this.fSodium = fSodium;
        this.fSugar = fSugar;
        this.fPotassium = fPotassium;
    }

    // Getters and setters
    // ... add getters and setters for all fields
}