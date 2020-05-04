package com.codecool.models;

public class Product {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private int rate;
    private String category;
    private Boolean is_available;

    public Product(int id, String name, float price, int quantity, int rate,
                    String category, Boolean is_available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.rate = rate;
        this.category = category;
        this.is_available = is_available;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }
}
