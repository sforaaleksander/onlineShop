package com.codecool.session;

public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;

    Session(){
        Login login = new Login();
    }
}
