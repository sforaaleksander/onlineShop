package com.codecool.models;

public class Product {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private int rate;
    private int categoryId;
    private Boolean isAvailable;

    public Product(int id, String name, float price, int quantity, int rate,
                   int categoryId, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.rate = rate;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
