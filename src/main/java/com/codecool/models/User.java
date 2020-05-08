package com.codecool.models;

import com.codecool.dao.ProductDao;

public abstract class User {
    private final int id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final ProductDao productDao;

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

    public String getName() {
        return name;
    }

    // accessor methods are required by external libraries
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
