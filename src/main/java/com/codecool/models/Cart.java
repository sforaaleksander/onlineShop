package com.codecool.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void addToCart(Product product) {
        products.merge(product, 1, Integer::sum);
    }

    public void editCart(Product product, int amount) {
        products.put(product, amount);
    }

    public void clearWhenZeroProducts() {
        products.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    public void emptyCart() {
        products.clear();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public float getTotalPrice() {
        // TODO
        return 0;
    }
}
