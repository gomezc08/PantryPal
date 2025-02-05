package com.gomezc.pantrypal;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;


@Repository
public class FoodJdbc {
    private final JdbcClient jdbcClient;

    public FoodJdbc(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Food> getAllItems() {
        return jdbcClient.sql("SELECT * FROM PantryPal")
            .query(Food.class)
            .list();
    }

    public void addFood(String foodName, String foodBrand, Integer foodCalories, Integer foodFat, Integer foodCarbs, Integer foodProtein, Integer foodSodium, Integer foodSugar) {
        // add to db.
        jdbcClient.sql("INSERT INTO PantryPal (fName, fBrand, fCalories, fFat, fCarbs, fProtein, fSodium, fSugar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
            .params(List.of(foodName, foodBrand, foodCalories, foodFat, foodCarbs, foodProtein, foodSodium, foodSugar))
            .update();
    }
}
