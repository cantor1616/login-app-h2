package com.example.controller;

import java.util.List;
import com.example.service.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/login/{username}/{password}")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    private Boolean login(@PathVariable String username, @PathVariable String password) {
        return userService.login(username, password);
    }

    @PostMapping("/users/add/{username}/{password}")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    private Boolean addUser(@PathVariable String username, @PathVariable String password) {
        return userService.addUser(username, password);
    }


    @DeleteMapping("/users/delete/{username}/{password}")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    private Boolean deleteUser(@PathVariable String username, @PathVariable String password) {
        return userService.deleteUser(username, password);
    }
}