package com.codecool.session;

import com.codecool.ui.IO;

import java.sql.SQLException;

public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;
    private IO io;

    public Session() {
        IO io = new IO();
        String userEmail = io.gatherInput("Email: ");
        String userPassword = io.gatherInput("Password: ");
        Login login = new Login(userEmail, userPassword);
        try {
            login.loginAttempt();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
