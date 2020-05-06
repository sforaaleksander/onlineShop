package com.codecool.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.dao.OrderDao;
import com.codecool.models.Order;
import com.codecool.models.User;
import com.codecool.ui.UI;

public class AdminMenuOperator extends MenuOperator {
    private Map<String, Runnable> ordersMenuMap;
    private Map<String, Runnable> usersMenuMap;

    public AdminMenuOperator(User user, UI ui) {
        super(user, ui);
        createMainMenuMap();
    }

    private void createMainMenuMap() {
        mainMenuMap = new HashMap<>();
        mainMenuMap.put("1", this::browseProducts);
        mainMenuMap.put("2", this::browseUsers);
        mainMenuMap.put("3", this::browseOrders);
    }

    private void browseOrders() {
        ordersMenuMap = new HashMap<>();
        ordersMenuMap.put("1", this::getAllOrders);
        ordersMenuMap.put("2", this::getOrdersByUserId);
        ordersMenuMap.put("3", this::getOrdersContaining);
        ordersMenuMap.put("0", this::exitProgram);
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

    private void browseUsers() {
        usersMenuMap = new HashMap<>();
        usersMenuMap.put("1", this::printAllUsers);
        usersMenuMap.put("2", this::printuUsersByUserId);
        usersMenuMap.put("3", this::printUsersContaining);
        boolean isRunning = true;
        do {
            ui.displayBrowseUsersMenu();
            String input = ui.gatherInput("What to do?: ");
            if (input.equals("0")) {
                isRunning = false;
                continue;
            }
            try {
                usersMenuMap.get(input).run();
            } catch (NullPointerException e) {
                System.out.println("No such option");
            }
        } while (isRunning);
    }

    private List<Order> getAllOrders() {
        return new OrderDao().getOrders("SELECT * FROM Orders;");
    }

    private List<Order> getOrdersContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        return new OrderDao().getOrders("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                                        + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                                        + "Products.Id = Order_products.Id_product WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }
}
