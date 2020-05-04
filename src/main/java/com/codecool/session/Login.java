package com.codecool.session;

import com.codecool.dao.AdminDao;
import com.codecool.dao.CustomerDao;
import com.codecool.models.Admin;
import com.codecool.models.Customer;

import java.util.List;

public class Login {
    private AdminDao adminsList;
    private CustomerDao customersList;

    public Login() {
        adminsList = new AdminDao();
        customersList = new CustomerDao();
    }

    public void validateLogin() {

    }

    public boolean isUserAdmin(String userEmail) {
        return adminsList.getAdmins().stream().anyMatch(o -> o.getEmail().equals(userEmail));
    }
}

