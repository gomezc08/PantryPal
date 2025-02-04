package com.gomezc.pantrypal;

import jakarta.validation.constraints.NotEmpty;

public record Food (
    @NotEmpty
    String foodName
){}