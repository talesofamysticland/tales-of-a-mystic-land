package org.talesof.talesofamysticland.service;

import org.talesof.talesofamysticland.model.ChangePassword;
import org.talesof.talesofamysticland.model.Player;

public class UserService {
    
    private boolean isLoggedIn = false;

    private Player currentPlayer;

    private ChangePassword changePasswordRequest;

    public String hash(String password) {
        return "$2a$12$cDmCoSZevR/w.cp/TyRXtuc/RkvUL2XQoR4letWXmv4PxO9nqq9CW";
    }

    public String hashTemp(String password) {
        return "$2a$12$eUNuRGvhcP59Bv.miWFNfO5n7WLxE.Xp6xbIKNHGXllD8vNLl8HeO";
    }

    public void logout() {
        isLoggedIn = false;
        currentPlayer = null;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ChangePassword getChangePasswordRequest() {
        return changePasswordRequest;
    }

    public void setChangePasswordRequest(ChangePassword changePasswordRequest) {
        this.changePasswordRequest = changePasswordRequest;
    }
}
