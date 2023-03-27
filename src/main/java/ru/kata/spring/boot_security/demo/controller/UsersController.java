//package ru.kata.spring.boot_security.demo.controller;
//
//import java.security.Principal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//@Controller
//@RequestMapping("/user")
//public class UsersController {
//    private final UserService userService;
//    public UsersController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping()
//    public String getUser(Model model, Principal principal) {
//        model.addAttribute("user", userService.findByUsername(principal.getName()));
//        return "/user";
//    }
//}
