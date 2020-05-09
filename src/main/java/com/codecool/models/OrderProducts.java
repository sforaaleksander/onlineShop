package com.codecool.models;

import java.util.List;

public class OrderProducts {
    private final int oderId;
    private final List<Integer> productsIdsForOrder;

    public OrderProducts(int orderId, List<Integer> productsIdsForOrder) {
        this.oderId = orderId;
        this.productsIdsForOrder = productsIdsForOrder;
    }

    public int getOderId() {
        return oderId;
    }

    public List<Integer> getProductsIdsForOrder() {
        return productsIdsForOrder;
    }
}
