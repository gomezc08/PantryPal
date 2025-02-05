package com.gomezc.pantrypal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodJdbc foodJdbc;
    private static final Logger log = LoggerFactory.getLogger(FoodController.class);
    private ObjectMapper mapper = new ObjectMapper();
    private File jsonFile;
    private JsonElements data;

    public FoodController(FoodJdbc foodJdbc) {
        this.foodJdbc = foodJdbc;
        this.jsonFile = new File("src\\main\\java\\com\\gomezc\\pantrypal\\JsonFile.json");
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // GET - Get all items in the pantry.
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getAllItems() {
        return foodJdbc.getAllItems();
    }

    // PUT - Add scanned food to the pantry (grabs data from JSON file).
    @PutMapping("/{food_name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFood() {
        try {
            this.data = mapper.readValue(jsonFile, JsonElements.class);
            // Loop through each FoodItem object and process it
            for (FoodItem item : data.getFoods()) {
                log.info("Adding item to pantry: " + item);
                foodJdbc.addFood(item.getFoodName(), item.getFoodBrand(), item.getFoodCalories(), item.getFoodFat(), item.getFoodCarbs(), item.getFoodProtein(), item.getFoodSodium(), item.getFoodSugar(), 1);
            }
        } 
        catch (Exception e) {
            log.error("Error parsing JSON: ", e);
        }
    }

    // UPDATE - Updates quantity of food item in pantry. 
    // NOT WORKING.
    @PutMapping("/{food_name}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void updateQuantity(int quantity) {
        foodJdbc.updateQuantity("food_name", quantity);
    }
}
