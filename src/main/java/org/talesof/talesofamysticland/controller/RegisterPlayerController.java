package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class RegisterPlayerController {

    private UserService userService;
    private NavigationService navigationService;

    @FXML
    private BorderPane root;

    @FXML
    private TextField txfUsername;

    @FXML
    private TextField txfEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private PasswordField pwfConfirmedPassword;

    public RegisterPlayerController(UserService userService, NavigationService navigationService) {
        this.userService = userService;
        this.navigationService = navigationService;
    }

    @FXML
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
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

        navigationService.navigateTo("title-screen.fxml");
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        navigationService.navigateTo("login.fxml");
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
