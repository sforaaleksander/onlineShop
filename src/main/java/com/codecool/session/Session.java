package com.codecool.session;

import com.codecool.models.Admin;
import com.codecool.models.Product;
import com.codecool.models.User;
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
        User loggedUser = null;
        String userEmail;
        Login login;
        do {
            userEmail = ui.gatherInput("Email: ").toLowerCase();
            String userPassword = ui.gatherInput("Password: ");
            login = new Login(userEmail, userPassword);
            try {
                loggedUser = login.loginAttempt(userEmail, userPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (loggedUser == null);
        loggedAs = userEmail;
        loggedAsAdmin = loggedUser instanceof Admin;
        ui.printTable(loggedUser.getProductsContaining("Name", "brea"), Product.class);
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
