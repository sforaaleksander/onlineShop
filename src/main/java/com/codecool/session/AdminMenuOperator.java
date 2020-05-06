package com.codecool.session;

import java.util.HashMap;
import java.util.Map;


import com.codecool.models.User;
import com.codecool.ui.UI;

public class AdminMenuOperator extends MenuOperator {
    private Map<String, Runnable> ordersMenuMap;
    private Map<String, Runnable> usersMenuMap;

    public AdminMenuOperator(User user, UI ui) {
        super(user, ui);
        createMainMenuMap();
        createUsersMenuMap();
        createOrdersMenuMap();
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::browseProducts);
        mainMenuMap.put("2", this::browseUsers);
        mainMenuMap.put("3", this::browseOrders);
    }

    private void createOrdersMenuMap() {
        ordersMenuMap = new HashMap<>();
        ordersMenuMap.put("1", this::getAllOrders);
        ordersMenuMap.put("2", this::getOrdersByUserId);
        ordersMenuMap.put("3", this::getOrdersContaining);
    }

    private void createUsersMenuMap() {
        usersMenuMap = new HashMap<>();
        usersMenuMap.put("1", this::printAllUsers);
        usersMenuMap.put("2", this::printuUsersByUserId);
        usersMenuMap.put("3", this::printUsersContaining);
    }
    
    private void browseUsers() {
        handleMenu(usersMenuMap, ui::displayBrowseUsersMenu);
    }

    private void browseOrders() {
        handleMenu(ordersMenuMap, ui::displayBrowseOrdersMenu);
    }

    private void printAllUsers() {
        printFromDB("SELECT * FROM Users;");
    }

    private void printuUsersByUserId() {
        String id = ui.gatherInput("Provide user id: ");
        printFromDB("SELECT * FROM Users WHERE id = " + id + ";");
    }

    private void printUsersContaining() {
        String column = ui.gatherInput("Provide column: ");
        String toSearch = ui.gatherInput("What to look for?: ");
        printFromDB("SELECT * FROM Users WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    private void getAllOrders() {
        printFromDB("SELECT * FROM Orders;");
    }

    private void getOrdersContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        printFromDB("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                                        + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                                        + "Products.Id = Order_products.Id_product WHERE " + column + " LIKE '%" + toSearch + "%';");
    }
}
