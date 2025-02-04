package com.gomezc.pantrypal;

import org.springframework.data.repository.ListCrudRepository;

public interface SpringDataRunRepo extends ListCrudRepository<Food, Long> {
    
}