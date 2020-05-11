package com.codecool.onlineshop.models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final int id;
    private final List<Product> orderProducts;
    private final int customerId;
    private final LocalDateTime createdAt;
    private final LocalDateTime paidAt;
    private final OrderStatus status;

    public Order(int id, List<Product> orderProducts, int customerId,
                 LocalDateTime createdAt, LocalDateTime paidAt, OrderStatus status) {
        this.id = id;
        this.orderProducts = orderProducts;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
}

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
