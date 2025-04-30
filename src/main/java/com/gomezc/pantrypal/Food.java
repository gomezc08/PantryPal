package com.gomezc.pantrypal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

// Class for food items; used to store data in database.
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
    private Integer fQuantity;

    // Default constructor required by JPA
    public Food() {}

    // Constructor with all fields
    public Food(String fName, String fBrand, Integer fCalories, Integer fFat, 
                Integer fCarbs, Integer fProtein, Integer fSodium, Integer fSugar, 
                Integer fPotassium, Integer fQuantity) {
        this.fName = fName;
        this.fBrand = fBrand;
        this.fCalories = fCalories;
        this.fFat = fFat;
        this.fCarbs = fCarbs;
        this.fProtein = fProtein;
        this.fSodium = fSodium;
        this.fSugar = fSugar;
        this.fPotassium = fPotassium;
        this.fQuantity = fQuantity;
    }

    // Getters and setters
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfBrand() {
        return fBrand;
    }

    public void setfBrand(String fBrand) {
        this.fBrand = fBrand;
    }

    public Integer getfCalories() {
        return fCalories;
    }  

    public void setfCalories(Integer fCalories) {
        this.fCalories = fCalories;
    }

    public Integer getfFat() {
        return fFat;
    }

    public void setfFat(Integer fFat) {
        this.fFat = fFat;
    }

    public Integer getfCarbs() {
        return fCarbs;
    }

    public void setfCarbs(Integer fCarbs) {
        this.fCarbs = fCarbs;
    }

    public Integer getfProtein() {
        return fProtein;
    }

    public void setfProtein(Integer fProtein) {
        this.fProtein = fProtein;
    }

    public Integer getfSodium() {
        return fSodium;
    }

    public void setfSodium(Integer fSodium) {
        this.fSodium = fSodium;
    }

    public Integer getfSugar() {
        return fSugar;
    }

    public void setfSugar(Integer fSugar) {
        this.fSugar = fSugar;
    }

    public Integer getfPotassium() {
        return fPotassium;
    }

    public void setfPotassium(Integer fPotassium) {
        this.fPotassium = fPotassium;
    }

    public Integer getfQuantity() {
        return fQuantity;
    }

    public void setfQuantity(Integer fQuantity) {
        this.fQuantity = fQuantity;
    }
}