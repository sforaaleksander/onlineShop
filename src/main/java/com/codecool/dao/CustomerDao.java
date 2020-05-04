package com.codecool.dao;

import com.codecool.models.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends Dao {

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Customers;");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                String phone = results.getString("phone");

                Customer customer = new Customer(id, name, surname, email, password, phone);
                customers.add(customer);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
