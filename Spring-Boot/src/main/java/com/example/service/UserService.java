package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers("Users");
    }

    public Boolean login(String username, String password){
        return userRepository.login("Users", username, password);
    }

    public Boolean addUser(String username, String password){
        return userRepository.addUser("Users", username, password);
    }

    public Boolean deleteUser(String username, String password){
        return userRepository.deleteUser("Users", username, password);
    }
}
