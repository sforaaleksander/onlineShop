package com.codecool;

import com.codecool.dao.ProductDao;
import com.codecool.dao.CustomerDao;
import com.codecool.models.Product;
import com.codecool.models.Customer;
import com.codecool.session.Login;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class App {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getCustomers();

        for (Customer customer : customers) {
            System.out.println(customer.getName() + " " + customer.getEmail());
        }

        // pretty table
        System.out.println(FlipTableConverters.fromIterable(customers, Customer.class));


        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProducts();

        // pretty table
        System.out.println(FlipTableConverters.fromIterable(products, Product.class));
    }
}
