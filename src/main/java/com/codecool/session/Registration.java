package com.codecool.session;

import com.codecool.dao.Dao;
import com.codecool.ui.UI;

import java.sql.SQLException;

public class Registration extends Dao {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private UI ui;

    Registration() {
        connect();
        ui = new UI();
        enterUserData();
        insertIntoDataBase();
    }

    private void enterUserData() {
        name = ui.gatherInput("Enter your name: ");
        surname = ui.gatherInput("Enter your surname: ");
        email = ui.gatherInput("Enter your email: ").toLowerCase();
        password = ui.gatherInput("Enter your password: ");
        phone = ui.gatherInput("Enter your phone: ");
    }

    private void insertIntoDataBase() {
        String sql = "INSERT INTO Users (name,surname, email, password, phone, Id_role)" +
                "VALUES ('" + name + "','" + surname + "','" + email + "','" + password + "','" + phone + "','" + 2 + "');";
        try {
            statement.executeUpdate(sql);
            ui.print("");
            statement.close();
            connection.close();
            ui.print("Registration successful");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
