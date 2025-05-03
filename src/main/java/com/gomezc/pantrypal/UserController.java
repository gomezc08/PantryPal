package com.gomezc.pantrypal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        // Optionally, return a redirect URL or user info (you decide how frontend handles it)
        return ResponseEntity.ok(user);  
    }
}
