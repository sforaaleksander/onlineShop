package com.codecool.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products;

    public Cart(){
        products = new HashMap<>();
    }

    public void addToCart(Product product){
        products.merge(product, 1, (prev, one) -> prev + one);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public float getTotalPrice(){
        return 0;
    }
}