package com.gomezc.pantrypal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

// Controller for food items; used to handle HTTP requests; REST API.
// Handles GET, PUT, DELETE requests.
// Uses the FoodJdbc to interact with the database.
@RestController
@RequestMapping("/food")
public class FoodController {
    private final Jdbc foodJdbc;
    private static final Logger log = LoggerFactory.getLogger(FoodController.class);
    private ObjectMapper mapper = new ObjectMapper();
    private File jsonFile;
    private JsonElements data;

    public FoodController(Jdbc foodJdbc) {
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

    // GET - Get all items in the pantry by category.
    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getCategoryItems(@PathVariable("category") String category) {
        List<Food> f = foodJdbc.getCategoryItems(category);
        log.info("Retrieved items from pantry by category: " + category);
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
                foodJdbc.addFood(item.getFoodName(), item.getFoodBrand(), item.getFoodCalories(), item.getFoodFat(), item.getFoodCarbs(), item.getFoodProtein(), item.getFoodSodium(), item.getFoodSugar(), item.getFoodServingQty(), item.getFoodServingUnit());
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
        foodJdbc.updatePantryQuantity(food, quantity);
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

    // homepage.
    @GetMapping("/home")
    public String home(@RequestParam String email, Model model) {
        User user = foodJdbc.getUser(email);
        List<Food> pantryItems = foodJdbc.getPantryForUser(email);

        model.addAttribute("user", user);
        model.addAttribute("pantryItems", pantryItems);
        return "home";
    }

}
