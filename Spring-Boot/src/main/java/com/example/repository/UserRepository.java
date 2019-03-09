package com.example.repository;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers(String table){
        String sql = "SELECT * FROM " + table + "";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

    public Boolean exists(String table, String username, String password){
        String sql = "SELECT count (*) FROM " + table + " WHERE username = \'" + username + "\' AND password = \'" + password + "\'";
        return jdbcTemplate.queryForObject(sql, Boolean.class);
    }

    public Boolean login(String table, String username, String password){
        return exists(table, username, password);
    }

    public Boolean addUser(String table, String username, String password){
        if (!exists(table, username, password)) {
            String sql = "INSERT INTO " + table + "(Username, Password) VALUES(\'" + username + "\', \'" + password + "\')";
            try{
                jdbcTemplate.execute(sql);
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteUser(String table, String username, String password){
        if (exists(table, username, password)) {
            String sql = "DELETE FROM Users WHERE username = \'" + username + "\' AND password = \'" + password + "\'";
            try{
                jdbcTemplate.execute(sql);
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
    }
}
