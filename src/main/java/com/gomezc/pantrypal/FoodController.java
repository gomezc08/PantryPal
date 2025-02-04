package com.gomezc.pantrypal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final SpringCRUD springCRUD;

    private static final Logger log = LoggerFactory.getLogger(FoodController.class);

    public FoodController(SpringCRUD springCRUD) {
        this.springCRUD = springCRUD;
    }

    // GET - get all items in the pantry.
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getAllItems() {
        return springCRUD.findAll();
    }

    // PUT - add an item to the pantry.
    @PutMapping("/{item}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFood(Food item) {
        log.info("Adding item to pantry: " + item + "; " + item.toString());
        springCRUD.save(item);
    }
}
