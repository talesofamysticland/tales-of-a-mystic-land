package org.talesof.talesofamysticland.service;

public class UserService {
    
    private boolean isLoggedIn = false;

    public String hash(String password) {
        return "$2a$12$cDmCoSZevR/w.cp/TyRXtuc/RkvUL2XQoR4letWXmv4PxO9nqq9CW";
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
