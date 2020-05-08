package com.codecool.models;

public class Product {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private int rate;
    private int categoryId;
    private Boolean isAvailable;

    public Product(int id, String name, float price, int quantity,
                   int categoryId, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }
}
