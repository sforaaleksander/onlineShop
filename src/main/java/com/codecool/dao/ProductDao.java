package com.codecool.dao;

import com.codecool.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends Dao {

    public List<Product> getProducts(String query) {
        List<Product> products = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                float price = results.getFloat("price");
                int quantity = results.getInt("quantity");
                int rate = results.getInt("rate");
                int categoryId = results.getInt("Id_category");
                Boolean isAvailable = results.getBoolean("is_available");

                Product product = new Product(id, name, price, quantity, rate, categoryId, isAvailable);
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

    protected void updateProduct(int id, String column, String newValue) {
        // String[] stringColumns = new String[]
        // Arrays.stream(stringColumns).anyMatch("s"::equals);
        newValue = column.toLowerCase().equals("name") ? newValue = "'" + newValue + "'" : newValue;
        update("Products", id, column, newValue);
    }
}
