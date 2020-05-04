package com.codecool.models;

import java.util.List;

public class OrderProducts {
    private int oderId;
    private List<Integer> productsIdsForOrder;

    public OrderProducts(int orderId, List<Integer> productsIdsForOrder) {
        this.oderId = orderId;
        this.productsIdsForOrder = productsIdsForOrder;
    }
}
