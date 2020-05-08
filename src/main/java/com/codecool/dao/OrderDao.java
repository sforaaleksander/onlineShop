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
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int id = results.getInt("Id");
                OrderProductsDao orderProductsDao = new OrderProductsDao();

                OrderProducts orderProducts = orderProductsDao.getOrderProducts(id);
                int customerId = results.getInt("Id_customer");
                OrderStatus orderStatus = OrderStatus.valueOf(results.getString("Order_status"));

                String createdAtString = results.getString("Created_at");
                LocalDateTime createdAt = LocalDateTime.parse(createdAtString);

                String paidAtString = results.getString("Paid_at");
                LocalDateTime paidAt;
                try {
                    paidAt = LocalDateTime.parse(paidAtString);
                } catch (java.time.format.DateTimeParseException e) {
                    paidAt = null;
                }

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

    public void updateOrder(String id, String column, String newValue) {
        newValue =  String.format("'%s'", newValue);
        update("Orders", id, column, newValue);
    }

    public void insertOrder(String[] values) {
        String[] columns = { "Id_customer", "Created_at", "Paid_at", "Order_status" };
        for (int i = 1; i < 4; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        insert("Orders", columns, values);
    }

    public void autoUpdateOrderStatus() {        
        String query = "UPDATE Orders SET Order_status = 'SENT' WHERE Cast ((julianday('now') - JulianDay(Paid_at)) * 24 * 60 As Integer) > 5;"
                     + "UPDATE Orders SET Order_status = 'DELIVERED' WHERE Cast ((julianday('now') - JulianDay(Paid_at)) * 24 * 60 As Integer) > 15;";

        connect();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
