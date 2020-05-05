package com.codecool;

import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.codecool.session.Session;
import com.codecool.ui.TableSupport;

import java.util.List;

public class App {
    public static void main(String[] args) {
        testTablePrint();
        new Session();
    }


    private static void testTablePrint() {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getUsers("SELECT * FROM Users;");

        System.out.println(TableSupport.fromIterable(users, User.class));

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts("SELECT * FROM Products;");

        System.out.println(TableSupport.fromIterable(products, Product.class));
    }
}
