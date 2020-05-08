package com.codecool;

import com.codecool.dao.OrderDao;
import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.Order;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.session.Session;
import com.codecool.ui.UI;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // testTablePrint();
        new Session();
    }

    private static void testTablePrint() {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getUsers("SELECT * FROM Users;");

        UI ui = new UI();
        ui.printTable(users, User.class);

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts("SELECT * FROM Products;");

        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.getOrders("SELECT * FROM Orders;");

        ui.printTable(orders, Order.class);

        ui.printTable(products, Product.class);
    }
}
