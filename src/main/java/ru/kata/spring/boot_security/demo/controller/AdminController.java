package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String ShowAllUsers(Model model) {
        model.addAttribute("users",userService.showAllUsers());
        return "index";
    }
    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "newUser";
    }
    @PostMapping
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showUserId(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user")  User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}