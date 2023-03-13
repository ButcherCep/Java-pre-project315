package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {
    List<User> showAllUsers();

    User showUserId(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}
