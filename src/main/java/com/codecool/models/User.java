package com.codecool.models;

import com.codecool.dao.ProductDao;

import java.util.List;

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

    public List<Product> getProductsContaining(String column, String toSearch) {
        return productDao.getProducts("WHERE " + column + " LIKE '%" + toSearch + "%'");
    }

    public List<Product> getProductsByCategory(String category) {
        return productDao.getProducts("JOIN Category ON Products.Id_category = Category.Id WHERE Category.Name = '" + category + "'");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
