package com.codecool.models;

import com.codecool.dao.ProductDao;

public abstract class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private ProductDao productDao;

    public User(int id, String email, String password, String name, String surname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        productDao = new ProductDao();
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public ProductDao getProductDao() {
        return productDao;
    }
}
