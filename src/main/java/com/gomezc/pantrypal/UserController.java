package com.gomezc.pantrypal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("")
public class UserController {
    private final Jdbc jdbc;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(Jdbc jdbc) {
        this.jdbc = jdbc;
    }

    // starter page.
    @GetMapping("/")
    public String loginScreen() {
        log.info("Login Screen"); 
        return "login.html";
    }

    // Login page.
    @PostMapping("/home")
    public String login(@RequestParam String email, @RequestParam String password) {
        log.info("Attempting to Login");
        User user = jdbc.getUser(email);

        if (user == null || !user.getuPassword().equals(password)) {
            return "redirect:/?error=true";
        }

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homeScreen() {
        return "home.html";
    }

    // Sign up page.
    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String password,
                                           @RequestParam(required = false) String phone,
                                           @RequestParam(required = false) Integer height,
                                           @RequestParam(required = false) Integer weight,
                                           @RequestParam(required = false) Integer age,
                                           @RequestParam(required = false) String gender) {
        log.info("Registering user");
        if (firstName == null || lastName == null || email == null || password == null) {
            return "First name, last name, email, and password are required";
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
            return "Error registering user";
        }

        log.info("User registered successfully");
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup.html"; 
    }

    // extra.
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = jdbc.getUser(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
}