package com.gomezc.pantrypal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Food {
    @JsonProperty("food_name")
    private String foodName;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("serving_qty")
    private double servingQty;

    @JsonProperty("serving_unit")
    private String servingUnit;

    @JsonProperty("serving_weight_grams")
    private double servingWeightGrams;

    @JsonProperty("nf_calories")
    private double calories;

    // Add other fields as necessary

    // Getters and setters
}
