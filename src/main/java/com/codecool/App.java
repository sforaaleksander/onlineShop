package com.codecool;

import com.codecool.dao.ProductDao;
import com.codecool.dao.CustomerDao;
import com.codecool.models.Product;
import com.codecool.models.Customer;
import com.codecool.session.Login;
import com.codecool.session.Session;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class App {
    public static void main(String[] args) {

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts();

        System.out.println(FlipTableConverters.fromIterable(products, Product.class));

        Session session = new Session();
    }
}
