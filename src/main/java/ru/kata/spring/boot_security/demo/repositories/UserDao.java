package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
public interface UserDao {
    List<User> showAllUsers();
    void saveUser(User user, List<String> selectResult);
    void updateUser(Long id, User user, List<String>  selectResult);
    void deleteUser(Long id);
    User newUser();
}
