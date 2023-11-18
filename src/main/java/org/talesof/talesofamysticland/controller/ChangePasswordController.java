package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChangePasswordController {

    private UserService userService;
    private NavigationService navigationService;

    @FXML
    private TextField txfEmail;

    @FXML
    private TextField txfVerificationToken;

    @FXML
    private PasswordField pwfNewPassword;

    @FXML
    private PasswordField pwfConfirmedNewPassword;

    public ChangePasswordController(UserService userService, NavigationService navigationService) {
        this.userService = userService;
        this.navigationService = navigationService;
    }

    @FXML
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onActionBtnSendToken() {
        navigationService.navigateTo("change-password2-token.fxml");
    }

    @FXML
    public void onActionBtnValidateToken() {
        navigationService.navigateTo("change-password3-new-password.fxml");
    }

    @FXML
    public void onActionBtnSetNewPassword() {
        navigationService.navigateTo("login.fxml");
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
