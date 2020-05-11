package com.codecool.onlineshop.session;

import java.util.HashMap;
import java.util.Map;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.models.User;
import com.codecool.onlineshop.ui.UI;

public class AdminMenuOperator extends MenuOperator {
    private Map<String, Runnable> ordersMenuMap;
    private Map<String, Runnable> usersMenuMap;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderDao orderDao;

    public AdminMenuOperator(User user, UI ui) {
        super(user, ui);
        createMainMenuMap();
        createUsersMenuMap();
        createOrdersMenuMap();
        productDao = new ProductDao();
        userDao = new UserDao();
        orderDao = new OrderDao();
        productsMenuMap.put("4", this::addProduct);
        productsMenuMap.put("5", this::editProduct);
        productsMenuMap.put("6", this::removeProduct);
        productsMenuMap.put("7", this::printAllCategories);
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
        usersMenuMap.put("1", userDao::printAll);
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

    private void printAllCategories() {
        userDao.printFromDB("Categories", "*", "");
    }

    private void printUsersByUserId() {
        String id = ui.gatherInput("Provide user id: ");
        userDao.print("*", "id = " + id);
    }

    private void printUsersContaining() {
        String column = ui.gatherInput("Provide column: ");
        String toSearch = ui.gatherInput("What to look for?: ");
        String condition = column + " LIKE '%" + toSearch + "%'";
        userDao.print("*", condition);
    }

    private void getAllOrders() {
        orderDao.autoUpdateOrderStatus();
        orderDao.printAll();
    }

    private void getOrdersContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        orderDao.autoUpdateOrderStatus();
        String table = "Orders JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON Products.Id = Order_products.Id_product";
        String columns = "Order_status, Created_at, Paid_at, Name, Price";
        String condition = column + " LIKE '%" + toSearch + "%'";
        orderDao.printFromDB(table, columns, condition);
    }

    private void editProduct() {
        System.out.println("EDITING PRODUCT");
        String productId = ui.gatherInput("Provide product ID to edit: ");
        String productColumn = ui.gatherInput("Provide the product's column you want to edit: ");
        String productUpdatedValue = ui.gatherInput("Provide new value for given column: ");

        productDao.updateProduct(productId, productColumn, productUpdatedValue);
    }

    private void addCategory() {
        System.out.println("ADDING CATEGORY");
        String name = ui.gatherInput("Provide the category's name");
        productDao.insertCategory(name);
    }

    private void addProduct() {
        System.out.println("ADDING PRODUCT");
        String name = ui.gatherInput("Provide the product's name");
        String price = ui.gatherInput("Provide the product's price");
        String quantity = ui.gatherInput("Provide the product's quantity");
        String categoryId = ui.gatherInput("Provide the product's Id_category");
        String isAvailable = ui.gatherInput("Provide the product's is_available");
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
        userDao.remove("Users", userId);
    }
}
