package com.codecool.models;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private OrderProducts orderProducts;
    private int customerId;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
    private OrderStatus status;

    public Order(int id, OrderProducts orderProducts, int customerId,
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

    public void setId(int id) {
        this.id = id;
    }

    public OrderProducts getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(OrderProducts orderProducts) {
        this.orderProducts = orderProducts;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

