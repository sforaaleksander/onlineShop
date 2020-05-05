package com.codecool.session;

import com.codecool.dao.AdminDao;
import com.codecool.dao.CustomerDao;
import com.codecool.dao.Dao;
import com.codecool.models.Admin;
import com.codecool.models.Customer;
import com.codecool.ui.IO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Login extends Dao {
    private String userEmail;
    private String userPassword;
    private boolean isUserAdmin;
    private String loggedUser;

    public Login(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public boolean getIsUserAdmin() {
        return isUserAdmin;
    }

    public boolean loginAttempt() throws SQLException {
        connect();
        boolean adminMatch = adminLoginAttempt(userEmail, userPassword);
        boolean customerMatch = customerLoginAttempt(userEmail, userPassword);
        return adminMatch || customerMatch;
    }

    private boolean adminLoginAttempt(String userEmail, String userPassword) throws SQLException {
        ResultSet emailResult = statement
                .executeQuery("SELECT * FROM Admins WHERE Email = '" + userEmail + "'AND Password = '" + userPassword + "';");
        if (!emailResult.next()) {
            isUserAdmin = false;
            return false;
        } else {
            isUserAdmin = true;
            return true;
        }
    }

    private boolean customerLoginAttempt(String userEmail, String userPassword) throws SQLException {
        ResultSet emailResult = statement
                .executeQuery("SELECT * FROM Customers WHERE Email = '" + userEmail + "' AND Password = '" + userPassword + "';");
        return emailResult.next();
    }


}


//    public boolean isUserAdmin(String userEmail) {
//        return adminsList.getAdmins().stream().anyMatch(o -> o.getEmail().equals(userEmail));
//    }

