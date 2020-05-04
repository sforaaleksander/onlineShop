package com.codecool;

import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.Product;
import com.codecool.models.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getUsers();

        for (User user : users) {
            System.out.println(user.getName() + " " + user.getEmail());
        }

        // pretty table
        System.out.println(FlipTableConverters.fromIterable(users, User.class));


        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts();

        // pretty table
        System.out.println(FlipTableConverters.fromIterable(products, Product.class));
    }
}
