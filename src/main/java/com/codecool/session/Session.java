package com.codecool.session;

import com.codecool.models.Admin;
import com.codecool.models.Cart;
import com.codecool.models.User;
import com.codecool.ui.UI;


public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;
    private final UI ui;
    private MenuOperator menuOperator;

    public Session() {
        ui = new UI();
        askIfLoginOrRegistration();
        User user = logIn();
        setMenuOperator(user);
        mainMenuChoice(user);
    }

    private void setMenuOperator(User user) {
        menuOperator = loggedAsAdmin ? new AdminMenuOperator(user, ui) : new CustomerMenuOperator(user, ui);
    }

    private void askIfLoginOrRegistration() {
        boolean registered = false;
        do {
            ui.displayLoginOrRegistrationMenu();
            String input = ui.gatherInput("What to do?: ");
            if (input.equals("2")) {
                Registration registration = new Registration();
            }else {
                registered = true;
            }
        }while (!registered);
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

    public MenuOperator getMenuOperator() {
        return menuOperator;
    }
}
