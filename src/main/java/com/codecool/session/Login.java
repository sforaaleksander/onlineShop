package com.codecool.session;

import com.codecool.dao.Dao;
import com.codecool.dao.UserDao;
import com.codecool.models.User;

import java.sql.SQLException;
import java.util.List;

public class Login extends Dao {

    public User loginAttempt(String userEmail, String userPassword) {
        connect();
        List<User> users = null;
        try {
            users = getMatchingUser(userEmail, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users.isEmpty() ? null : users.get(0);

    }

    private List<User> getMatchingUser(String userEmail, String userPassword) throws SQLException {
        return new UserDao().getUsers(
                "SELECT * FROM Users WHERE email = '" + userEmail + "' AND password = '" + userPassword + "';");
    }
}
