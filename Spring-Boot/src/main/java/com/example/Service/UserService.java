package com.example.Service;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public JdbcTemplate jtm;

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM Users";
        return jtm.query(sql, new BeanPropertyRowMapper(User.class));
    }

    public Boolean exists(String username, String password){
        String sql = "SELECT count (*) FROM Users WHERE username = \'" + username + "\' AND password = \'" + password + "\'";
        return jtm.queryForObject(sql, Boolean.class);
    }

    public Boolean login(String username, String password){
        return exists(username, password);
    }

    public Boolean addUser(String username, String password){
        if (!exists(username, password)) {
            String sql = "INSERT INTO Users(Username, Password) VALUES(\'" + username + "\', \'" + password + "\')";
            jtm.execute(sql);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteUser(String username, String password){
        if (exists(username, password)) {
            String sql = "DELETE FROM Users WHERE username = \'" + username + "\' AND password = \'" + password + "\'";
            jtm.execute(sql);
            return true;
        } else {
            return false;
        }
    }
}
