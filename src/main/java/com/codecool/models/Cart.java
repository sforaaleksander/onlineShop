package com.codecool.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<Product> products;

    public Cart(){
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getTotalPrice(){
        return 0;
    }
}