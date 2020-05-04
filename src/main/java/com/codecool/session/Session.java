package com.codecool.session;

import com.codecool.ui.IO;

public class Session {
    private String loggedAs;
    private boolean loggedAsAdmin;
    private long sessionTime;
    private IO io;

    public Session(){
        IO io = new IO();
        Login login = new Login(io);
        loggedAs = login.userLogging();
    }
}
