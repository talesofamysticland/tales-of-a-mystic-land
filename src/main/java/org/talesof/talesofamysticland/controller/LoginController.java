package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class LoginController {

    private UserService userService;
    private NavigationService navigationService;

    @FXML
    private BorderPane root;

    @FXML
    private TextField txfUsernameOrEmail;

    @FXML
    private PasswordField pwfPassword;

    public LoginController(UserService userService, NavigationService navigationService) {
        this.userService = userService;
        this.navigationService = navigationService;
    }

    @FXML
    public void onClickImgOpenConfigurations() {
        // TODO
    }

    @FXML
    public void onActionHplRedirectToChangePassword() {
        // TODO
    }

    @FXML
    public void onActionBtnLogin() {
        // TODO
    }

    @FXML
    public void onActionHplRedirectToRegisterPlayer() {
        // TODO
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
