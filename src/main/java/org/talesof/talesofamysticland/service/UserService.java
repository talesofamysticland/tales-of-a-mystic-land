package org.talesof.talesofamysticland.service;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    
    private boolean isLoggedIn = false;

    public String passwordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
