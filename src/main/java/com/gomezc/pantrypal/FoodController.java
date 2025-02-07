package com.gomezc.pantrypal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Food> f = foodJdbc.getAllItems();
        log.info("Retrieved all items from pantry.");
        return f;

    }

    // PUT - Add scanned food to the pantry (grabs data from JSON file).
    @PutMapping("/{food_name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFood() {
        try {
            this.data = mapper.readValue(jsonFile, JsonElements.class);
            // Loop through each FoodItem object and process it
            for (FoodItem item : data.getFoods()) {
                foodJdbc.addFood(item.getFoodName(), item.getFoodBrand(), item.getFoodCalories(), item.getFoodFat(), item.getFoodCarbs(), item.getFoodProtein(), item.getFoodSodium(), item.getFoodSugar(), 1);
            }
        } 
        catch (Exception e) {
            log.error("Error parsing JSON: ", e);
        }

        log.info("Added food item to pantry.");
    }

    // UPDATE - Updates quantity of food item in pantry. 
    @PutMapping("/{food_name}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void updateQuantity(@PathVariable ("food_name") String food, @PathVariable int quantity) {
        log.info("Updating quantity of " + food + " to " + quantity);
        foodJdbc.updateQuantity(food, quantity);
        log.info("Updated quantity of " + food + " to " + quantity);
    }

    // DELETE - deletes a food item.
    @DeleteMapping("/delete/{food_name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFood(@PathVariable ("food_name") String food) {
        log.info("Deleting " + food + " from pantry.");
        foodJdbc.deleteFood(food);
        log.info("Deleted " + food + " from pantry.");
    }
}
