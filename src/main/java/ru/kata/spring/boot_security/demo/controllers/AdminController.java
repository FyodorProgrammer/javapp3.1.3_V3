package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entenies.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
@EnableTransactionManagement
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("new")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("saveUser")
    public String addUser(@ModelAttribute("user") User userForm) {

        userService.saveUser(userForm);
        return "redirect:/admin";
    }

    @GetMapping("{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("{id}")
    public String edit(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}