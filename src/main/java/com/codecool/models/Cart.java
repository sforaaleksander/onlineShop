package com.codecool.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart(){
        products = new ArrayList<>();
    }

    public void addToCart(Product product){
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getTotalPrice(){
        return 0;
    }
}