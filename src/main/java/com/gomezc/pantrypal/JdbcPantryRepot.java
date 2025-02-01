package com.gomezc.pantrypal;


import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

@Repository
public class JdbcPantryRepot {
    private final JdbcClient jdbcClient;



    public JdbcPantryRepot(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // GET - get all items in the pantry.
    public List<Food> getAllItems() {
        return jdbcClient.sql("SELECT * FROM pantry")
            .query(Food.class)
            .list();
    }

    // PUT - add an item to the pantry.
    // public void addItem(Food item) {
    //     jdbcClient.sql("INSERT INTO pantry (fname) VALUES (?)")
    //         .params(List.of(item.fname().toString()))
    //         .update();
    // }
}
