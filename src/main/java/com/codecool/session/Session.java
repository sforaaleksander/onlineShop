package com.codecool.session;

import com.codecool.models.Admin;
import com.codecool.models.Cart;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.ui.UI;

import java.util.List;

public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;
    private final UI ui;
    private Cart cart;
    private MenuOperator menuOperator;

    public Session() {
        ui = new UI();
        User user = logIn();
        this.cart = new Cart();
        setMenuOperator(user);
        mainMenuChoice(user);
    }

    private void setMenuOperator(User user) {
        menuOperator = loggedAsAdmin ? new AdminMenuOperator(user, ui) : new CustomerMenuOperator(user, ui);
    }

    private User logIn() {
        User loggedUser = null;
        String userEmail;
        Login login = new Login();
        do {
            userEmail = ui.gatherInput("Email: ").toLowerCase();
            String userPassword = ui.gatherInput("Password: ");
            loggedUser = login.loginAttempt(userEmail, userPassword);
        } while (loggedUser == null);
        ui.print("Logged in");
        loggedAs = userEmail;
        loggedAsAdmin = loggedUser instanceof Admin;
        return loggedUser;
    }

    private void mainMenuChoice(User loggedUser) {
        menuOperator.handleMenu(
            menuOperator.getMainMenuMap(), loggedAsAdmin ? ui::displayAdminMainMenu : ui::displayCustomerMainMenu);
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

    public Cart getCart() {
        return cart;
    }

    public MenuOperator getMenuOperator() {
        return menuOperator;
    }
}
