package com.codecool.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.models.Admin;
import com.codecool.models.Order;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.ui.UI;
import com.codecool.dao.OrderDao;
import com.codecool.dao.ProductDao;

public class HandleMenuOperation {
    private User user;
    private UI ui;
    private Map<String, Runnable> mainMenuMap;

    HandleMenuOperation(User user, UI ui) {
        this.user = user;
        this.ui = ui;
        if (user instanceof Admin) {
            createAdminMainMenuMap();
        } else {
            createCustomerMainMenuMap();
        }
    }


    private void createCustomerMainMenuMap() {
        mainMenuMap = new HashMap<>();
        // mainMenuMap.put("1", this::showMyInfo);
        mainMenuMap.put("2", this::getAllProducts);
        mainMenuMap.put("3", this::getProductsByCategory);
        mainMenuMap.put("4", this::getProductsContaining);
        // mainMenuMap.put("c", this::openCart);
        mainMenuMap.put("0", this::exitProgram);
    }

    private void createAdminMainMenuMap() {
        mainMenuMap = new HashMap<>();
        mainMenuMap.put("1", this::getAllProducts);
        mainMenuMap.put("2", this::getProductsByCategory);
        mainMenuMap.put("3", this::getProductsContaining);
        mainMenuMap.put("4", this::getAllOrders);
        mainMenuMap.put("5", this::getOrdersByUserId);
        mainMenuMap.put("6", this::getOrdersContaining);
        mainMenuMap.put("0", this::exitProgram);
    }

    private List<Product> getProductsContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        return new ProductDao().getProducts("SELECT * FROM Products WHERE " + column + " LIKE '%" + toSearch + "%';");
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

    private List<Product> getProductsByCategory() {
        String category;
        category = ui.gatherInput("Provide category: ");
        return new ProductDao().getProducts("SELECT * FROM Products JOIN Category ON Products.Id_category = Category.Id WHERE Category.Name = '" + category + "';");
    }

    private List<Order> getOrdersByUserId() {
        String userId;
        userId = ui.gatherInput("Provide userId: ");
        return new OrderDao().getOrders("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                                        + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                                        + "Products.Id = Order_products.Id_product WHERE Orders.Id_customer = " + userId + ";");
    }

    private List<Product> getAllProducts() {
        return new ProductDao().getProducts("SELECT * FROM Products;");
    }

    private List<Order> getAllOrders() {
        return new OrderDao().getOrders("SELECT * FROM Orders;");
    }

    private void exitProgram() {

    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }

    public void setMainMenuMap(Map<String, Runnable> mainMenuMap) {
        this.mainMenuMap = mainMenuMap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
