package com.codecool.session;

import com.codecool.dao.AdminDao;
import com.codecool.dao.CustomerDao;
import com.codecool.dao.Dao;
import com.codecool.models.Admin;
import com.codecool.models.Customer;
import com.codecool.models.User;
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

    public User loginAttempt() throws SQLException {
        connect();
        User userMatch;
        List<Admin> admins = adminLoginAttempt(userEmail, userPassword);
        if (!admins.isEmpty()) {
            return admins.get(0);
        } else {
            List<Customer> customers = customerLoginAttempt(userEmail, userPassword);
            if (!customers.isEmpty()) {
                return customers.get(0);
            }
        }
        return null;
    }

    private List<Admin> adminLoginAttempt(String userEmail, String userPassword) throws SQLException {
        return new AdminDao().getAdmins("where email = '" + userEmail + "' AND password = '" + userPassword + "';");

    }

    private List<Customer> customerLoginAttempt(String userEmail, String userPassword) throws SQLException {
        return new CustomerDao()
                .getCustomers("where email = '" + userEmail + "' AND password = '" + userPassword + "';");
    }

}

// public boolean isUserAdmin(String userEmail) {
// return adminsList.getAdmins().stream().anyMatch(o ->
// o.getEmail().equals(userEmail));
// }
