package com.codecool.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
    private Map<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void addToCart(Product product) {
        products.merge(product, 1, (prev, one) -> prev + one);
    }

    public void editCart(Product product, int amount) {
        products.put(product, amount);
    }

    public void clearWhenZeroProducts() {
        // Iterator<Map.Entry<Integer, String>> 
        // iterator = products.entrySet().iterator(); 
        // }
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public float getTotalPrice(){
        return 0;
    }
}