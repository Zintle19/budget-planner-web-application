package com.example.budget.controller;

import com.example.budget.model.User;
import com.example.budget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public RedirectView register(@RequestParam String fullname,
                                  @RequestParam String username,
                                  @RequestParam String password) {
        if (userRepo.findByUsername(username) != null) {
            return new RedirectView("/register.html?error=exists");
        }

        User user = new User();
        user.setFullName(fullname);
        user.setUsername(username);
        user.setPassword(password); // 🔐 Encrypt in production!
        userRepo.save(user);

        return new RedirectView("/login.html");
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username,
                              @RequestParam String password) {
        User user = userRepo.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return new RedirectView("/dashboard.html?name=" + user.getFullName());
        }

        return new RedirectView("/login.html?error=true");
    }
}