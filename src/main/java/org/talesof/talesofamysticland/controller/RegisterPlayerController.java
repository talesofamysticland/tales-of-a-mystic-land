package org.talesof.talesofamysticland.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterPlayerController {

    @FXML
    private TextField txfUsername;

    @FXML
    private TextField txfEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private PasswordField pwfConfirmedPassword;

    @FXML
    public void onClickImgOpenConfigurations() {
        // TODO
    }

    @FXML
    public void onActionBtnRegisterPlayer() {

        String username = txfUsername.getText();
        String email = txfEmail.getText();
        String password = pwfPassword.getText().trim();
        String confirmedPassword = pwfConfirmedPassword.getText().trim();

        if(!confirmedPassword.equals(password)) {
            // TODO
        } else {
            System.out.println(username + ", " + email + ", " + password + ", " + confirmedPassword);
        }
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        // TODO
    }

    @FXML
    public void onActionBtnBack() {
        // TODO
    }
}
