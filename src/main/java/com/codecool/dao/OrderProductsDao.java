package com.codecool.dao;

import com.codecool.models.OrderProducts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductsDao extends Dao {

    public OrderProducts getOrderProducts(int orderId) throws SQLException{
        List<Integer> productsIdsOfOrder = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Order_products WHERE Id = " + orderId + ";");
            while (results.next()) {
                int productId = results.getInt("Id_product");
                productsIdsOfOrder.add(productId);
            }
            OrderProducts orderProducts = new OrderProducts(orderId, productsIdsOfOrder);

            results.close();
            statement.close();
            connection.close();
            return orderProducts;
        } catch (SQLException e) {
            throw new SQLException ();
        }
    }
}