package org.talesof.talesofamysticland.service;

import org.talesof.talesofamysticland.bcrypt.BCrypt;
import org.talesof.talesofamysticland.model.ChangePassword;
import org.talesof.talesofamysticland.model.Player;

public class UserService {
    
    private boolean isLoggedIn = false;

    private Player currentPlayer;

    private ChangePassword changePasswordRequest;

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean check(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
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
