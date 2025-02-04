package com.gomezc.pantrypal;

import jakarta.validation.constraints.NotEmpty;

public record Food (
    @NotEmpty
    String fName,
    @NotEmpty
    String fBrand,
    Integer fCalories,
    Integer fFat,
    Integer fCarbs,
    Integer fProtein,
    Integer fSodium,
    Integer fSugar,
    Integer fPotassium
){}