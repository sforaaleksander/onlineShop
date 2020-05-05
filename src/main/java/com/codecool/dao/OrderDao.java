package com.codecool.dao;

import com.codecool.models.Order;
import com.codecool.models.OrderProducts;
import com.codecool.models.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDao extends Dao {
    public List<Order> getOrders(String query) {
        List<Order> orders = new ArrayList<>();
        connect();

        try {
            // TODO select orders only for logged user
            ResultSet results = statement.executeQuery("SELECT * FROM Orders" + query + ";");
            while (results.next()) {
                int id = results.getInt("id");
                OrderProductsDao orderProductsDao = new OrderProductsDao();

                OrderProducts orderProducts = orderProductsDao.getOrderProducts(id);
                int customerId = results.getInt("Id_customer");
                // TODO ENUM
                OrderStatus orderStatus = OrderStatus.valueOf(results.getString("Order_status"));

                String createdAtString = results.getString("Created_at");
                LocalDateTime createdAt = LocalDateTime.parse(createdAtString);

                String paidAtString = results.getString("Paid_at");
                LocalDateTime paidAt = LocalDateTime.parse(paidAtString);

                Order order = new Order(id, orderProducts, customerId, createdAt, paidAt, orderStatus);
                orders.add(order);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}