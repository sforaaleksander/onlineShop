package com.codecool.models;

public class Customer extends User {
    private String name;
    private String surname;
    private String phone;

    public Customer(int id, String email, String password, String name, String surname, String phone) {
        super(id, email, password);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
