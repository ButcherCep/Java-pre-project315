package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserDao;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, UserDao {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepository.findByName(username);
        return new org.springframework.security.core.userdetails
                .User(myUser.getUsername(),
                myUser.getPassword(),
                myUser.getRoles());
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User showUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setRoles(Collections.singleton(roleRepository.getById(1L)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
