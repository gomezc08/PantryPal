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
import java.util.List;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/homepage")
public class HomepageController {
    private final Jdbc jdbc;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public HomepageController(Jdbc jdbc) {
        this.jdbc = jdbc;
    }
    
    @GetMapping("/home") 
    public String homeScreen(@RequestParam String email, Model model) {
        log.info("Homepage");
        
        if (email == null || email.isEmpty()) {
            return "redirect:/";
        }

        List<Food> pantryItems = jdbc.getPantryForUser(email);
        User user = jdbc.getUser(email);
        
        if (user == null) {
            return "redirect:/";
        }

        // Debug logging
        log.info("User data being passed to view - First Name: " + user.getuFirstName());
        log.info("User data being passed to view - Email: " + user.getuEmail());
        
        // assigning model attributes for access in home.html.
        model.addAttribute("user", user);
        model.addAttribute("pantryItems", pantryItems);

        log.info("User: " + user.getuEmail());
        for(Food food : pantryItems) {
            log.info(food.getfName());
        }

        return "dashboard";
    }

    @PostMapping("/profilepage")
    public String profilePage(@RequestParam String email, Model model) {
        log.info("Profile Page");
        
        if (email == null || email.isEmpty()) {
            return "redirect:/";
        }

        User user = jdbc.getUser(email);
        
        if (user == null) {
            return "redirect:/";
        }

        // Debug logging
        log.info("User data being passed to view - First Name: " + user.getuFirstName());
        log.info("User data being passed to view - Email: " + user.getuEmail());

        // assigning model attributes for access in profile.html.
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/add-food")
    public String addFood2() {
        log.info("adding food to pantry");
        return "QRscanner";
    }
}
