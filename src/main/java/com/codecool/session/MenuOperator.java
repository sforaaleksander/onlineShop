package com.codecool.session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.dao.Dao;
import com.codecool.dao.UserDao;
import com.codecool.models.Admin;
import com.codecool.models.User;
import com.codecool.ui.UI;

public abstract class MenuOperator extends Dao {
    protected Map<String, Runnable> productsMenuMap;
    protected Map<String, Runnable> mainMenuMap;
    protected Map<String, Runnable> userProfileMenuMap;
    protected UI ui;
    protected User user;

    MenuOperator(User user, UI ui) {
        this.user = user;
        this.ui = ui;
        mainMenuMap = new HashMap<>();
        createBrowseProducts();
        createUserProfileMenuMap();
    }

    protected void displayProfileDetails() {
        List<User> users= new ArrayList<>();
        users.add(user);
        ui.printTable(users, User.class);
    }

    private void createBrowseProducts() {
        productsMenuMap = new HashMap<>();
        productsMenuMap.put("1", this::getAllProducts);
        productsMenuMap.put("2", this::getProductsByCategory);
        productsMenuMap.put("3", this::getProductsContaining);
    }

    private void createUserProfileMenuMap() {
        userProfileMenuMap = new HashMap<>();
        userProfileMenuMap.put("1", this::editUserDetails);
    }

    protected void userProfile() {
        displayProfileDetails();
        handleMenu(userProfileMenuMap, ui::displayUserProfileMenu);
    }


    protected void editUserDetails() {
        String id = Integer.toString(user.getId());
        String column = ui.gatherInput("Provide column: ");
        String newValue = ui.gatherInput("New value for the column: ");
        UserDao userDao = new UserDao();
        userDao.updateUser(id, column, newValue);
        this.user = userDao.getUsers("SELECT * FROM Users WHERE Id = " + id + ";").get(0);
    }

    protected void browseProducts() {
        if (user instanceof Admin) {
            handleMenu(productsMenuMap, ui::displayAdminBrowseProductsMenu);
        } else {
            handleMenu(productsMenuMap, ui::displayCustomerBrowseProductsMenu);
        }
    }

    public void handleMenu(Map<String, Runnable> menuMap, Runnable uiMenu) {
        boolean isRunning = true;
        do {
            uiMenu.run();
            String input = ui.gatherInput("What to do?: ");
            if (input.equals("0")) {
                isRunning = false;
                continue;
            }
            try {
                menuMap.get(input).run();
            } catch (NullPointerException e) {
                System.out.println("No such option");
            }
        } while (isRunning);
    }

    private void getAllProducts() {
        printFromDB("SELECT * FROM Products;");
    }

    protected void printFromDB(String query) {
        connect();
        try {
            ResultSet results = statement.executeQuery(query);
            ui.printTableFromDB(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void getProductsContaining() {
        String column = ui.gatherInput("Provide column: ");
        String toSearch = ui.gatherInput("What to look for?: ");

        printFromDB("SELECT * FROM Products WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    protected void getProductsByCategory() {
        String category = ui.gatherInput("Provide category: ");

        printFromDB("SELECT * FROM Products JOIN Categories" 
                    + " ON Products.Id_category = Categories.Id WHERE Categories.Name = '"
                    + category + "';");
    }

    protected void getOrdersByUserId() {
        String userId = user instanceof Admin ? ui.gatherInput("Provide userId: ") : Integer.toString(user.getId());

        printFromDB("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders "
                    + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON "
                    + "Products.Id = Order_products.Id_product WHERE Orders.Id_customer = " + userId + ";");
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }
}
