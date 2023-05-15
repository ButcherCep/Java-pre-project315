package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import java.util.Set;

@RestController
//@RequestMapping("/api")
public class AdminRESTController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> getRole() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/myPrincipal")
    public ResponseEntity<User> getPrincipal(Principal principal) {
        return new ResponseEntity<>(userService.findByUsername(principal.getName()),
                HttpStatus.OK);
    }

    @GetMapping("/oneUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/makeUser")
    public ResponseEntity<User> creatRestUser(@RequestBody User user) {
        List<String> list1 = user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
        List<Role> list2 = roleService.listByRole(list1);
        user.setRoles(Set.copyOf(list2));
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/changeUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userUp, @PathVariable Long id) {
        List<String> list1 = userUp.getRoles().stream().map(role->role.getRole()).collect(Collectors.toList());
        List<Role> list2 = roleService.listByRole(list1);
        userUp.setRoles(Set.copyOf(list2));
        userService.edit(id, userUp);
        return new ResponseEntity<>(userUp, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
