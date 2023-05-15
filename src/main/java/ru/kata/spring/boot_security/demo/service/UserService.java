package ru.kata.spring.boot_security.demo.service;

import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserDao;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.ArrayList;
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
        return new UserEmail(myUser.getEmail(), myUser.getUsername(),
                myUser.getPassword(), myUser.getRoles());
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Transactional
    @Override
    public User newUser() {
        return new User();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    //    @Transactional
//    @Override
//    public void saveUser(User userNew, List<String> selectResult) {
//        List<Role> list = new ArrayList<>();
//        list.add(roleRepository.
//                findByName("ROLE_"+selectResult
//                        .stream()
//                        .map(Object::toString)
//                        .collect(Collectors
//                                .joining(","))));
//        userNew.setRoles(list);
//        userNew.setName(userNew.getName());
//        userNew.setPassword(passwordEncoder.encode(userNew.getPassword()));
//        userRepository.save(userNew);
//    }
    @Override
    @Transactional
    public void add(User userNew) {
        userNew.setName(userNew.getName());
        userNew.setSurname(userNew.getSurname());
        userNew.setPassword(passwordEncoder.encode(userNew.getPassword()));
        userRepository.save(userNew);
    }

    @Transactional
    @Override
    public void edit(Long id, User userUp) {
        User userToUpdate = userRepository.getById(id);
        userToUpdate.setName(userUp.getName());
        userToUpdate.setSurname(userUp.getSurname());
        userToUpdate.setEmail(userUp.getEmail());
        userToUpdate.setAge(userUp.getAge());
        userRepository.save(userToUpdate);
    }
//    @Transactional
//    @Override
//    public void updateUser(Long id, User user, List<String> selectResult) {
//        User userToUpdate = userRepository.getById(id);
//        userToUpdate.setName(user.getName());
//        userToUpdate.setSurname(user.getSurname());
//        userToUpdate.setEmail(user.getEmail());
//        userToUpdate.setAge(user.getAge());
//        List<Role> list = new ArrayList<>();
//        list.add(roleRepository.
//                findByName("ROLE_" + selectResult
//                        .stream()
//                        .map(Object::toString)
//                        .collect(Collectors
//                                .joining(","))));
//        userToUpdate.setRoles(list);
//        userRepository.save(userToUpdate);
//    }
}
