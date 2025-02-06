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

    public void addFood(String foodName, String foodBrand, Integer foodCalories, Integer foodFat, Integer foodCarbs, Integer foodProtein, Integer foodSodium, Integer foodSugar, Integer foodQuantity) {
        // add to db.
        jdbcClient.sql("INSERT INTO PantryPal (fName, fBrand, fCalories, fFat, fCarbs, fProtein, fSodium, fSugar, fQuantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .params(List.of(foodName, foodBrand, foodCalories, foodFat, foodCarbs, foodProtein, foodSodium, foodSugar, foodQuantity))
            .update();
    }

    public void updateQuantity(String foodName, Integer foodQuantity) {
        try {
            jdbcClient.sql("UPDATE PantryPal SET fQuantity = ? WHERE fName = ?")
                      .params(List.of(foodQuantity, foodName))
                      .update();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
