package com.codecool.session;

import com.codecool.dao.UserDao;
import com.codecool.models.User;

import java.util.List;

import com.codecool.dao.Dao;
import com.codecool.ui.UI;

public class Registration extends Dao {
    private UI ui;

    Registration() {
        connect();
        ui = new UI();
        enterUserData();
    }

    private void enterUserData() {
        String email = ui.gatherInput("Enter your email: ").toLowerCase();
        List<User> sameEmailUsers = new UserDao().getUsers("SELECT * FROM Users WHERE Email = '" + email + "';");
        if (!sameEmailUsers.isEmpty()) {
            ui.gatherEmptyInput("User with this email already exists");
            return;
        }
        if (!isValidEmailAddress(email)) {
            ui.gatherEmptyInput("Invalid email address");
            return;
        }
        String password = ui.gatherInput("Enter your password: ");
        String name = ui.gatherInput("Enter your name: ");
        String surname = ui.gatherInput("Enter your surname: ");
        String phone = ui.gatherInput("Enter your phone: ");
        String Id_role = "2";
        String[] values = { name, surname, email, password, phone, Id_role };
        new UserDao().insertUser(values);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
