package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entenies.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping
@EnableTransactionManagement
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers() {
        return "index";
    }


    @GetMapping("/user")
    public String getAllUsers(Principal principal, Model model) {
        Optional<User> user = userService.getUserByName(principal.getName());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", principal.getName()));
        }
        model.addAttribute("user", user.get());
        return "user";
    }
}
