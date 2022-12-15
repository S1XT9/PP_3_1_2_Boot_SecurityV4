package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminContollerForAll {


    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final String redirect = "redirect:/admin/users";

    @Autowired
    public AdminContollerForAll(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/")
    public String getRedirectToUserPage() {
        return "redirect:/login";
    }
    @GetMapping("/admin")
    public String showAdmin(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("admin", userService.showUser(user.getId()));
        return "/adminView/admin";
    }

    @GetMapping("/admin/users/{id}")
    public String showUserForAdmin(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "/adminView/showUser";
    }


    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("listOfUsers", userService.getAllUsers());
        return "/adminView/allUsers";
    }

    @GetMapping("/admin/users/new")
    public String getViewForNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/adminView/new";
    }

    @PostMapping("/admin/users/newUser")
    public String addUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return redirect;
    }


    @GetMapping("/admin/users/{id}/edit")
    public String getViewForUpdateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showUser(id));
        return "/adminView/edit";
    }

    @PatchMapping("/admin/users/{id}/editUser")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return redirect;
    }

    @DeleteMapping("/admin/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return redirect;
    }
}