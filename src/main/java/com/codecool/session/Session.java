package com.codecool.session;

import com.codecool.ui.IO;

import java.sql.SQLException;

public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;
    private IO io;

    public Session() {
        io = new IO();
        loggingIn();
    }

    private void loggingIn() {
        boolean logged = false;
        String userEmail;
        Login login;
        do {
            userEmail = io.gatherInput("Email: ");
            String userPassword = io.gatherInput("Password: ");
            login = new Login(userEmail, userPassword);
            try {
                logged = login.loginAttempt();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (!logged);
        loggedAs = userEmail;
        loggedAsAdmin = login.getIsUserAdmin();
    }

    public String getLoggedAs() {
        return loggedAs;
    }

    public void setLoggedAs(String loggedAs) {
        this.loggedAs = loggedAs;
    }

    public boolean isLoggedAsAdmin() {
        return loggedAsAdmin;
    }

    public void setLoggedAsAdmin(boolean loggedAsAdmin) {
        this.loggedAsAdmin = loggedAsAdmin;
    }

    public long getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(long sessionTime) {
        this.sessionTime = sessionTime;
    }

    public IO getIo() {
        return io;
    }

    public void setIo(IO io) {
        this.io = io;
    }
}
