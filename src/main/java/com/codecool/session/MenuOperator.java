package com.codecool.session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.dao.Dao;
import com.codecool.dao.OrderDao;
import com.codecool.dao.ProductDao;
import com.codecool.models.Admin;
import com.codecool.models.Order;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.ui.UI;
import com.jakewharton.fliptables.FlipTableConverters;

public abstract class MenuOperator extends Dao {
    protected Map<String, Runnable> browseProductsMap;
    protected UI ui;
    protected User user;

    MenuOperator(User user, UI ui) {
        this.user = user;
        this.ui = ui;
        createBrowseProducts();
    }

    public UI getUi() {
        return ui;
    }

    private void createBrowseProducts() {
        browseProductsMap = new HashMap<>();
        browseProductsMap.put("2", this::getAllProducts);
        browseProductsMap.put("3", this::getProductsByCategory);
        browseProductsMap.put("4", this::getProductsContaining);
        browseProductsMap.put("c", this::openCart);
    }

    protected void browseProducts() {
        printProducts("SELECT * FROM Products;");
    }

    public Map<String, Runnable> getBrowseProductsMap() {
        return browseProductsMap;
    }

    private void getAllProducts() {
        printProducts("SELECT * FROM Products;");
    }

    protected void printProducts(String query) {
        connect();
        try {
            ResultSet results = statement.executeQuery(query);
            System.out.println(FlipTableConverters.fromResultSet(results));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected List<Product> getProductsContaining() {
        String column;
        String toSearch;
        column = ui.gatherInput("Provide column: ");
        toSearch = ui.gatherInput("What to look for?: ");
        return new ProductDao().getProducts("SELECT * FROM Products WHERE " + column + " LIKE '%" + toSearch + "%';");
    }

    protected List<Product> getProductsByCategory() {
        String category;
        category = ui.gatherInput("Provide category: ");
        return new ProductDao().getProducts(
                "SELECT * FROM Products JOIN Category ON Products.Id_category = Category.Id WHERE Category.Name = '"
                        + category + "';");
    }

    protected List<Order> getOrdersByUserId() {
        String userId;

        if (user instanceof Admin) {
            userId = ui.gatherInput("Provide userId: ");
        } else {
            userId = Integer.toString(user.getId());
        }

        return new OrderDao().getOrders("SELECT Order_status, Created_at, Paid_at, Name, Price FROM Orders"
                                        + "JOIN Order_products ON Order_products.Id_order = Orders.Id JOIN Products ON"
                                        + "Products.Id = Order_products.Id_product WHERE Orders.Id_customer = " + userId + ";");
    }

    protected void openCart() {
    }

    protected void exitProgram() {

    }
}
