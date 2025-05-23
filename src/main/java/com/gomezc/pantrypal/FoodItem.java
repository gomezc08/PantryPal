package com.gomezc.pantrypal;

import com.fasterxml.jackson.annotation.JsonProperty;

// Class for food items; used to parse JSON data.
public class FoodItem {
    @JsonProperty("food_name")
    private String foodName;
    @JsonProperty("brand_name")
    private String foodBrand;
    @JsonProperty("nf_calories")
    private Integer foodCalories;
    @JsonProperty("nf_total_fat")
    private Integer foodFat;
    @JsonProperty("nf_total_carbohydrate")
    private Integer foodCarbs;
    @JsonProperty("nf_protein")
    private Integer foodProtein;
    @JsonProperty("nf_sodium")
    private Integer foodSodium;
    @JsonProperty("nf_sugars")
    private Integer foodSugar;
    @JsonProperty("serving_qty")
    private Integer foodServingQty;
    @JsonProperty("serving_unit")
    private String foodServingUnit;

    // Getters and setters.
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodBrand() {
        return foodBrand;
    }

    public void setFoodBrand(String foodBrand) {
        this.foodBrand = foodBrand;
    }

    public Integer getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(Integer foodCalories) {
        this.foodCalories = foodCalories;
    }

    public Integer getFoodFat() {
        return foodFat;
    }

    public void setFoodFat(Integer foodFat) {
        this.foodFat = foodFat;
    }

    public Integer getFoodCarbs() {
        return foodCarbs;
    }

    public void setFoodCarbs(Integer foodCarbs) {
        this.foodCarbs = foodCarbs;
    }

    public Integer getFoodProtein() {
        return foodProtein;
    }

    public void setFoodProtein(Integer foodProtein) {
        this.foodProtein = foodProtein;
    }

    public Integer getFoodSodium() {
        return foodSodium;
    }

    public void setFoodSodium(Integer foodSodium) {
        this.foodSodium = foodSodium;
    }

    public Integer getFoodSugar() {
        return foodSugar;
    }

    public void setFoodSugar(Integer foodSugar) {
        this.foodSugar = foodSugar;
    }

    public Integer getFoodServingQty() {
        return foodServingQty;
    }

    public void setFoodServingQty(Integer foodServingQty) {
        this.foodServingQty = foodServingQty;
    }
    public String getFoodServingUnit() {
        return foodServingUnit;
    }

    public void setFoodServingUnit(String foodServingUnit) {
        this.foodServingUnit = foodServingUnit;
    }
}