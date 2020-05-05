package com.codecool.models;

import java.util.List;

public class OrderProducts {
    private int oderId;
    private List<Integer> productsIdsForOrder;

    public OrderProducts(int orderId, List<Integer> productsIdsForOrder) {
        this.oderId = orderId;
        this.productsIdsForOrder = productsIdsForOrder;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public List<Integer> getProductsIdsForOrder() {
        return productsIdsForOrder;
    }

    public void setProductsIdsForOrder(List<Integer> productsIdsForOrder) {
        this.productsIdsForOrder = productsIdsForOrder;
    }
}
