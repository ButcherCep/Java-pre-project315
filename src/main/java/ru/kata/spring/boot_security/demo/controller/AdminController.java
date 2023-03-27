//package ru.kata.spring.boot_security.demo.controller;
//
//import java.security.Principal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserService userService;
//    private final RoleService roleService;
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//    @GetMapping()
//    public String ShowAllUsers(Model model, Principal principal) {
//        model.addAttribute("user", userService.findByUsername(principal.getName()));
//        model.addAttribute("users", userService.showAllUsers());
//        model.addAttribute("roles", roleService.getAllRoles());
//        model.addAttribute("userNew", userService.newUser());
//        return "/admin";
//    }
//    @PostMapping("/new")
//    public String create(@ModelAttribute("userNew") User userNew,
//                         @RequestParam(value = "checkedRoles") List<String> selectResult) {
//        userService.saveUser(userNew,selectResult);
//        return "redirect:/admin";
//    }
//    @PostMapping("/updateUser/{id}")
//    public String updateUser(@ModelAttribute("user")  User user,
//                             @PathVariable("id") Long id,
//                             @RequestParam(value = "Selector") List<String> selectResult) {
//        userService.updateUser(id, user, selectResult);
//        return "redirect:/admin";
//    }
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//}