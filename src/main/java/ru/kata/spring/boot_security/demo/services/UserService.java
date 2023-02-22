package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entenies.Role;
import ru.kata.spring.boot_security.demo.entenies.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    List<User> getAllUsers();

    User getUserById(Long id);

    Optional<User> getUserByName(String name);

    void saveUser(User user);

    void deleteUser(Long id);

    List<Role> getAllRoles();


}
