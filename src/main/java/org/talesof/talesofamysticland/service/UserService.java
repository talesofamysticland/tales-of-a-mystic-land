package org.talesof.talesofamysticland.service;

public class UserService {
    
    private boolean isLoggedIn = false;

    public String passwordHash(String password) {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
