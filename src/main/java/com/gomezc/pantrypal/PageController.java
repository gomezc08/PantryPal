package com.gomezc.pantrypal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@Controller
public class PageController { 

    private final Jdbc jdbc;
    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    public PageController(Jdbc jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/")
    public String loginScreen() {
        log.info("Login Screen"); 
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        log.info("Attempting to Login");
        User user = jdbc.getUser(email);

        if (user == null || !user.getuPassword().equals(password)) {
            return "redirect:/?error=true";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String homeScreen() {
        return "home.html";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup.html"; 
    }

}
