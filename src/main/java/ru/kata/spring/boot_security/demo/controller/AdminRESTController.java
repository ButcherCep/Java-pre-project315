package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/adminpanel/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/adminpanel/allRoles")
    public ResponseEntity<List<Role>> getRole() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @GetMapping("/adminpanel/principle")
    public ResponseEntity<User> getPrincipal (Principal principal) {
        return new ResponseEntity<>(userService.findByUsername(principal.getName()),
                HttpStatus.OK);
    }

    @PostMapping("/adminpanel/new")
    public ResponseEntity<User> creatUser (@RequestBody User userNew,
                                           @RequestParam(value = "checkedRoles") List<String> selectResult) {
        userService.saveUser(userNew,selectResult);
        return new ResponseEntity<>(userNew, HttpStatus.OK);
    }
    @PostMapping("/adminpanel/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userUp,
                                           @PathVariable("id") Long id,
                                           @RequestParam(value = "Selector") List<String> selectResult) {
        userService.updateUser(id, userUp, selectResult);
        return new ResponseEntity<>(userUp, HttpStatus.OK);
    }
    @DeleteMapping("/adminpanel/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
