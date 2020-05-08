package com.codecool.session;

import java.util.HashMap;
import java.util.Map;

import com.codecool.dao.OrderDao;
import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.User;
import com.codecool.ui.UI;

public class AdminMenuOperator extends MenuOperator {
    private Map<String, Runnable> ordersMenuMap;
    private Map<String, Runnable> usersMenuMap;
    private ProductDao productDao;

    public AdminMenuOperator(User user, UI ui) {
        super(user, ui);
        createMainMenuMap();
        createUsersMenuMap();
        createOrdersMenuMap();
        productDao = new ProductDao();
        productsMenuMap.put("4", this::addProduct);
        productsMenuMap.put("5", this::editProduct);
        productsMenuMap.put("6", this::removeProduct);
        productsMenuMap.put("7", this::getAllCategories);
        productsMenuMap.put("8", this::addCategory);
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
        usersMenuMap.put("2", this::printUsersByUserId);
        usersMenuMap.put("3", this::printUsersContaining);
        usersMenuMap.put("4", this::removeUser);
    }

    private void browseUsers() {
        handleMenu(usersMenuMap, ui::displayBrowseUsersMenu);
    }

    private void browseOrders() {
        handleMenu(ordersMenuMap, ui::displayBrowseOrdersMenu);
    }

    private void getAllCategories() {
        printFromDB("SELECT * FROM Categories;");
    }

    private void printAllUsers() {
        printFromDB("SELECT * FROM Users;");
    }

    private void printUsersByUserId() {
        String id = ui.gatherInput("Provide user id: ");
        printFromDB("SELECT * FROM Users WHERE id = " + id + ";");
    }

    private void printUsersContaining() {
        String column = ui.gatherInput("Provide column: ");
        String toSearch = ui.gatherInput("What to look for?: ");
        printFromDB("SELECT * FROM Users WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    private void getAllOrders() {
        new OrderDao().autoUpdateOrderStatus();
        printFromDB("SELECT * FROM Orders;");
    }

    private void getOrdersContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        new OrderDao().autoUpdateOrderStatus();
        printFromDB("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders "
                + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON "
                + "Products.Id = Order_products.Id_product WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    private void editProduct() {
        System.out.println("EDITING PRODUCT");
        String productId = ui.gatherInput("Provide product ID to edit: ");
        String productColumn = ui.gatherInput("Provide product's column you want to edit: ");
        String productUpdatedValue = ui.gatherInput("Provide new value for given column: ");

        productDao.updateProduct(productId, productColumn, productUpdatedValue);
    }

    private void addCategory() {
        System.out.println("ADDING CATEGORY");
        String name = ui.gatherInput("Provide category's name");
        productDao.insertCategory(name);
    }

    private void addProduct() {
        System.out.println("ADDING PRODUCT");
        String name = ui.gatherInput("Provide product's name");
        String price = ui.gatherInput("Provide product's price");
        String quantity = ui.gatherInput("Provide product's quantity");
        String categoryId = ui.gatherInput("Provide product's Id_category");
        String isAvailable = ui.gatherInput("Provide product's is_available");
        String[] values = { name, price, quantity, categoryId, isAvailable };
        productDao.insertProduct(values);
    }

    private void removeProduct() {
        System.out.println("REMOVING PRODUCT");
        String productId = ui.gatherInput("Provide product ID to remove: ");
        productDao.remove("Products", productId);
    }

    private void removeUser() {
        System.out.println("REMOVING USER");
        String userId = ui.gatherInput("Provide user ID to remove: ");
        new UserDao().remove("Users", userId);
    }
}
