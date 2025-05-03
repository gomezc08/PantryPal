package com.gomezc.pantrypal;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

// Repository for food items; used to interact DIRECTLY  with the database.
// FoodController uses JDBC to interact with the database.
@Repository
public class Jdbc {
    private final JdbcClient jdbcClient;

    public Jdbc(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // GET methods.
    public List<Food> getAllItems() {
        return jdbcClient.sql("SELECT * FROM FoodItem")
            .query(Food.class)
            .list();
    }

    public List<Food> getCategoryItems(String category) {
        return jdbcClient.sql("SELECT * FROM FoodItem WHERE fCategory = ?")
            .params(List.of(category))
            .query(Food.class)
            .list();
    }

    public User getUser(String email) {
        return jdbcClient.sql("SELECT * FROM User WHERE uEmail = ?")
            .params(List.of(email))
            .query(User.class)
            .optional()
            .orElse(null);
    }

    // PUT methods.
    public void addFood(String foodName, String foodBrand, Integer foodCalories, Integer foodFat, Integer foodCarbs, Integer foodProtein, Integer foodSodium, Integer foodSugar, Integer foodServingQty, String foodServingUnit) {
        jdbcClient.sql("INSERT INTO FoodItem (fName, fBrand, fCalories, fFat, fCarbs, fProtein, fSodium, fSugar, fQuantity, fServingUnit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .params(List.of(foodName, foodBrand, foodCalories, foodFat, foodCarbs, foodProtein, foodSodium, foodSugar, foodServingQty, foodServingUnit))
            .update();
    }

    public void addUser(String fName, String lName, String email, String password, String phone, int h, int w, int age, String gender) {
        jdbcClient.sql("INSERT INTO Users (uFirstName, uLastName, uEmail, uPassword, uPhone, uHeight, uWeight, uAge, uGender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .params(List.of(fName, lName, email, password, phone, h, w, age, gender))
            .update(); 
    }

    public void addUserActivities(String email, String activity) {
        jdbcClient.sql("INSERT INTO UserActivity (uEmail, activity) VALUES (?, ?)")
            .params(List.of(email, activity))
            .update();
    }

    public void addPantryItem(String pName, String uEmail, int quantity) {
        jdbcClient.sql("INSERT INTO Pantry (pName, uEmail, pQuantity) VALUES (?, ?, ?)")
            .params(List.of(pName, uEmail, quantity))
            .update();
    }

    public void addDailyNutritionGoal(String uEmail, int calories, int fat, int carbs, int protein, int sodium, int sugar, int potassium) {
        jdbcClient.sql("""
            INSERT INTO UserDailyNutritionGoal (uEmail, calories, fat, carbs, protein, sodium, sugar, potassium)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """)
        .params(List.of(uEmail, calories, fat, carbs, protein, sodium, sugar, potassium))
        .update();
    }

    public void addPantryCategory(String pName, String category, String fName) {
        jdbcClient.sql("""
            INSERT INTO PantryCategory (pName, category, fName)
            VALUES (?, ?, ?)
        """)
        .params(List.of(pName, category, fName))
        .update();
    }

    public void addRecipe(String rName, String mealType, int totalCalories, int totalFat, int totalCarbs, int totalProtein, int totalSodium, int totalSugar, int totalPotassium) {
        jdbcClient.sql("""
            INSERT INTO Recipe (rName, mealType, totalCalories, totalFat, totalCarbs, totalProtein, totalSodium, totalSugar, totalPotassium)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """)
        .params(List.of(rName, mealType, totalCalories, totalFat, totalCarbs, totalProtein, totalSodium, totalSugar, totalPotassium))
        .update();
    }
    
    public void addRecipeIngredient(String rName, String fName, int servingQty, String servingUnit) {
        jdbcClient.sql("""
            INSERT INTO RecipeIngredient (rName, fName, fServingQty, fServingUnit)
            VALUES (?, ?, ?, ?)
        """)
        .params(List.of(rName, fName, servingQty, servingUnit))
        .update();
    }
    
    public void addRecipeInstruction(String rName, int step, String stepDescription) {
        jdbcClient.sql("""
            INSERT INTO RecipeInstruction (rName, step, stepDescription)
            VALUES (?, ?, ?)
        """)
        .params(List.of(rName, step, stepDescription))
        .update();
    }

    public void addUnit(String uName, String uAbbreviation, String unitType) {
        jdbcClient.sql("""
            INSERT INTO UnitTable (uName, uAbbreviation, unitType)
            VALUES (?, ?, ?)
        """)
        .params(List.of(uName, uAbbreviation, unitType))
        .update();
    }

    
    public void addRecipeCalendarEntry(String uEmail, java.sql.Date date, String mealType, String rName) {
        jdbcClient.sql("""
            INSERT INTO RecipeCalendar (uEmail, date, mealType, rName)
            VALUES (?, ?, ?, ?)
        """)
        .params(List.of(uEmail, date, mealType, rName))
        .update();
    }

    
    public void addNutritionLogEntry(String uEmail, java.sql.Date date, int calories, int fat, int carbs, int protein, int sodium, int sugar, int potassium) {
        jdbcClient.sql("""
            INSERT INTO NutritionLog (uEmail, date, totalCalories, totalFat, totalCarbs, totalProtein, totalSodium, totalSugar, totalPotassium)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """)
        .params(List.of(uEmail, date, calories, fat, carbs, protein, sodium, sugar, potassium))
        .update();
    }    

    // UPDATE methods.
    public void updatePantryQuantity(String foodName, Integer foodQuantity) {
        try {
            jdbcClient.sql("UPDATE FoodItem SET fQuantity = ? WHERE fName = ?")
                      .params(List.of(foodQuantity, foodName))
                      .update();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // DELETE methods.
    public void deleteFood(String foodName) {
        jdbcClient.sql("DELETE FROM FoodItem WHERE fName = ?")
            .params(List.of(foodName))
            .update();
    }
}