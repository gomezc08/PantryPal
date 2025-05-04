package com.gomezc.pantrypal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {
    private final Jdbc jdbc;

    public UserController(Jdbc jdbc) {
        this.jdbc = jdbc;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        User user = jdbc.getUser(email);

        if (user == null || !user.getuPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid email or password");
        }

        return ResponseEntity.ok(user);  
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String password,
                                           @RequestParam(required = false) String phone,
                                           @RequestParam(required = false) Integer height,
                                           @RequestParam(required = false) Integer weight,
                                           @RequestParam(required = false) Integer age,
                                           @RequestParam(required = false) String gender) {
        if (firstName == null || lastName == null || email == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("First name, last name, email, and password are required");
        }
        try {
            jdbc.addUser(firstName, lastName, email, password, 
                         phone != null ? phone : "", 
                         height != null ? height : 0, 
                         weight != null ? weight : 0, 
                         age != null ? age : 0,
                         gender != null ? gender : "");
        } 
        catch (Exception e) {            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = jdbc.getUser(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
}