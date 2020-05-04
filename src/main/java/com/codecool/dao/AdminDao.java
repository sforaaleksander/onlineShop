package com.codecool.dao;

import com.codecool.models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends Dao {

    public List<Admin> getUsers() {
        List<Admin> admins = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Users;");
            while (results.next()) {
                int id = results.getInt("id");
                String email = results.getString("email");
                String password = results.getString("password");

                Admin admin = new Admin(id, email, password);
                admins.add(admin);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }
}
