package com.codecool.models;

public class Customer extends User {
    private String phone;

    public Customer(int id, String email, String password, String name, String surname, String phone) {
        super(id, email, password, name, surname);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
