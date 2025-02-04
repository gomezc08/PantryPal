package com.gomezc.pantrypal;

import org.springframework.data.repository.ListCrudRepository;

public interface SpringCRUD extends ListCrudRepository<Food, String> {
    
}