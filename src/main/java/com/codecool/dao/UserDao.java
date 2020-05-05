package com.codecool.dao;

import com.codecool.models.Admin;
import com.codecool.models.Customer;
import com.codecool.models.Role;
import com.codecool.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {

    public List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Users " + query + ";");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                String phone = results.getString("phone");

                if (results.getInt("Id_role") == Role.CUSTOMER.getRoleId()) {
                    User customer = new Customer(id, email, password, name, surname, phone);
                    users.add(customer);
                } else {
                    User admin = new Admin(id, email, password, name, surname);
                    users.add(admin);
                }
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}