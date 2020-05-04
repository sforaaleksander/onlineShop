package com.codecool.dao;

import com.codecool.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends Dao {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Products;");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                float price = results.getFloat("price");
                int quantity = results.getInt("quantity");
                int rate = results.getInt("rate");
                String category = results.getString("category");
                Boolean is_available = results.getBoolean("is_available");

                Product product = new Product(id, name, price, quantity, rate, category, is_available);
                products.add(product);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
