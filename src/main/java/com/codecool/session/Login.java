package com.codecool.session;

import com.codecool.dao.Dao;
import com.codecool.dao.UserDao;
import com.codecool.models.User;

import java.sql.SQLException;
import java.util.List;

public class Login extends Dao {


    public Login(String userEmail, String userPassword) {
        try {
            loginAttempt(userEmail, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User loginAttempt(String userEmail, String userPassword) throws SQLException {
        connect();
        List<User> users = matchUser(userEmail, userPassword);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    private List<User> matchUser(String userEmail, String userPassword) throws SQLException {
        return new UserDao().getUsers("SELECT * FROM Users WHERE email = '" + userEmail + "' AND password = '" + userPassword + "';");
    }
}