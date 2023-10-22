package org.talesof.talesofamysticland.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterPlayerController {

    @FXML
    private TextField txfUsername;

    @FXML
    private TextField txfEmail;

    @FXML
    private TextField txfPassword;

    @FXML
    private TextField txfConfirmedPassword;

    @FXML
    public void onMouseClickedOpenConfigurations() {
        // TODO
    }

    @FXML
    public void onActionRegisterPlayer() {

        String username = txfUsername.getText();
        String email = txfEmail.getText();
        String password = txfPassword.getText().trim();
        String confirmedPassword = txfConfirmedPassword.getText().trim();

        if(!confirmedPassword.equals(password)) {
            // TODO
        } else {
            System.out.println(username + ", " + email + ", " + password + ", " + confirmedPassword);
        }
    }
    
}
