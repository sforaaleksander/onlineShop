package com.codecool.onlineshop.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.models.Admin;
import com.codecool.onlineshop.models.Order;
import com.codecool.onlineshop.models.User;
import com.codecool.onlineshop.ui.UI;

public abstract class MenuOperator {
    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final UserDao userDao;
    protected Map<String, Runnable> productsMenuMap;
    protected final Map<String, Runnable> mainMenuMap;
    protected Map<String, Runnable> userProfileMenuMap;
    protected final UI ui;
    protected User user;

    MenuOperator(User user, UI ui) {
        this.user = user;
        this.ui = ui;
        mainMenuMap = new HashMap<>();
        createBrowseProducts();
        createUserProfileMenuMap();
        this.productDao = new ProductDao();
        this.orderDao = new OrderDao();
        this.userDao = new UserDao();
    }

    protected void displayProfileDetails() {
        List<User> users = new ArrayList<>();
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
        productDao.printAll();
    }

    protected void getProductsContaining() {
        String column = ui.gatherInput("Provide column: ");
        String toSearch = ui.gatherInput("What to look for?: ");

        productDao.print("*", column + " LIKE '%" + toSearch + "%';");
    }

    protected void getProductsByCategory() {
        String category = ui.gatherInput("Provide category: ");
        String table = "Products JOIN Categories ON Products.Id_category = Categories.Id";
        String condition = "Categories.Name = '" + category + "'";
        productDao.printFromDB(table, "*", condition);
    }

    protected void getOrdersByUserId() {
        String userId = user instanceof Admin
                      ? ui.gatherInput("Provide userId: ")
                      : Integer.toString(user.getId());
        orderDao.autoUpdateOrderStatus();

        if (!areAnyOrdersByUser(orderDao, userId)){
            return;
        }
        String columns = "Order_status, Created_at, Paid_at, Name, Price";
        String table = "Orders JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON Products.Id = Order_products.Id_product";
        String condition = "Orders.Id_customer = " + userId;
        orderDao.printFromDB(table, columns, condition);
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }

    private boolean areAnyOrdersByUser(OrderDao orderDao, String userId) {
        List<Order> ordersByUser= orderDao.getOrders("SELECT * FROM Orders WHERE Id_customer = " + userId + ";");
        if (ordersByUser.isEmpty()) {
            ui.gatherEmptyInput("No orders yet");
            return false;
        }
        return true;
    }
}
