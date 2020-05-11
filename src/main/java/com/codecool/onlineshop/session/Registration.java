package com.codecool.onlineshop.session;

import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.models.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codecool.onlineshop.ui.UI;

public class Registration {
    private final UI ui;

    Registration() {
        ui = new UI();
        enterUserData();
    }

    private void enterUserData() {
        String email = ui.gatherInput("Enter your email: ").toLowerCase();
        UserDao userDao = new UserDao();
        List<User> sameEmailUsers = userDao.getUsers("SELECT * FROM Users WHERE email = \"" + email + "\";");
        if (emailIsAlreadyTaken(sameEmailUsers)) {
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
        String[] values = {name, surname, email, password, phone, Id_role};
        userDao.insertUser(values);
    }

    private boolean emailIsAlreadyTaken(List<User> sameEmailUsers) {
        return !sameEmailUsers.isEmpty();
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
                "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
