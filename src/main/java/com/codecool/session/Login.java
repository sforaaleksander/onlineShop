package com.codecool.session;

import com.codecool.dao.AdminDao;
import com.codecool.dao.CustomerDao;
import com.codecool.models.Admin;
import com.codecool.models.Customer;
import com.codecool.ui.IO;

import java.util.List;

public class Login {
    private AdminDao adminsList;
    private CustomerDao customersList;
    private IO io;

    public Login(IO io) {
        adminsList = new AdminDao();
        customersList = new CustomerDao();
        this.io = io;
    }

    public String userLogging() {
        String userEmail = io.gatherInput("Email: ");
        String userPassword = io.gatherInput("Password: ");
        if (adminsList.getAdmins().stream().anyMatch(o -> o.getEmail().equals(userEmail))) {
            return "";
        }
        return "";
    }

    public boolean isUserAdmin(String userEmail) {
        return adminsList.getAdmins().stream().anyMatch(o -> o.getEmail().equals(userEmail));
    }
}

