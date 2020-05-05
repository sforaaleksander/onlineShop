package com.codecool;

import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.Product;
import com.codecool.models.Customer;
import com.codecool.session.Session;
import com.codecool.ui.TableSupport;

import java.util.List;

public class App {
    public static void main(String[] args) {
        testTablePrint();
        Session session = new Session();
    }


    private static void testTablePrint() {
        UserDao userDao = new UserDao();
        List<Customer> customers = userDao.getCustomers("");

        System.out.println(TableSupport.fromIterable(customers, Customer.class));

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts("");

        System.out.println(TableSupport.fromIterable(products, Product.class));
    }
}
