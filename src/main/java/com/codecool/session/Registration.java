package com.codecool.session;

import com.codecool.dao.UserDao;
import com.codecool.dao.Dao;
import com.codecool.ui.UI;

public class Registration extends Dao {
    private UI ui;
    private String[] values;

    Registration() {
        connect();
        ui = new UI();
        enterUserData();
    }

    private void enterUserData() {
        String name = "'" + ui.gatherInput("Enter your name: ") + "'";
        String surname = "'" + ui.gatherInput("Enter your surname: ") + "'";
        String email = "'" + ui.gatherInput("Enter your email: ").toLowerCase() + "'";
        String password = "'" + ui.gatherInput("Enter your password: ") + "'";
        String phone = "'" + ui.gatherInput("Enter your phone: ") + "'";
        String Id_role = "2";
        values = new String[]{name, surname, email, password, phone, Id_role};
        new UserDao().insertUser(values);
    }
}
