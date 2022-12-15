package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;


public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(Long id, User user);

    User showUser(Long id);

    //для доступа с приведением
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roleSet);

    User findByUsername(String username);
}
