package com.codecool.session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.codecool.dao.Dao;
import com.codecool.models.Admin;
import com.codecool.models.User;
import com.codecool.ui.UI;

public abstract class MenuOperator extends Dao {
    protected Map<String, Runnable> productsMenuMap;
    protected Map<String, Runnable> mainMenuMap;
    protected UI ui;
    protected User user;

    MenuOperator(User user, UI ui) {
        this.user = user;
        this.ui = ui;
        mainMenuMap = new HashMap<>();
        createBrowseProducts();
    }

    public UI getUi() {
        return ui;
    }

    private void createBrowseProducts() {
        productsMenuMap = new HashMap<>();
        productsMenuMap.put("1", this::getAllProducts);
        productsMenuMap.put("2", this::getProductsByCategory);
        productsMenuMap.put("3", this::getProductsContaining);
        productsMenuMap.put("c", this::openCart);
    }

    protected void browseProducts() {
        handleMenu(productsMenuMap, ui::displayBrowseProductsMenu);
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

    public Map<String, Runnable> getBrowseProductsMap() {
        return productsMenuMap;
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
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        printFromDB("SELECT * FROM Products WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    protected void getProductsByCategory() {
        String category;
        category = ui.gatherInput("Provide category: ");
        printFromDB("SELECT * FROM Products JOIN Category" 
                    + " ON Products.Id_category = Category.Id WHERE Category.Name = '"
                    + category + "';");
    }

    protected void getOrdersById() {
        String userId;

        if (user instanceof Admin) {
            userId = ui.gatherInput("Provide userId: ");
        } else {
            userId = Integer.toString(user.getId());
        }

        printFromDB("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                    + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                    + "Products.Id = Order_products.Id_product WHERE Orders.Id_customer = " + userId + ";");
    }

    protected void openCart() {
    }

    protected void exitProgram() {

    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }
}
