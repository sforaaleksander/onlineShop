package com.codecool.session;

import com.codecool.ui.UI;

import java.sql.SQLException;

public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;
    private final UI ui;

    public Session() {
        ui = new UI();
        loggingIn();
    }

    private void loggingIn() {
        boolean logged = false;
        String userEmail;
        Login login;
        do {
            userEmail = ui.gatherInput("Email: ");
            String userPassword = ui.gatherInput("Password: ");
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

    public UI getUi() {
        return ui;
    }
}
