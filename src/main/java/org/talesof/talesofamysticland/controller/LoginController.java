package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.service.FormErrorListeningService;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class LoginController {

    private UserService userService;
    private NavigationService navigationService;
    private FormErrorListeningService formErrorListeningService;
    private PlayerDAO playerDAO;

    @FXML
    private BorderPane root;

    @FXML
    private TextField txfUsernameOrEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Label lblInvalidCredentials;

    public LoginController(
        UserService userService, 
        NavigationService navigationService, 
        FormErrorListeningService formErrorListeningService,
        PlayerDAO playerDAO) {

        this.userService = userService;
        this.navigationService = navigationService;
        this.formErrorListeningService = formErrorListeningService;
        this.playerDAO = playerDAO;
    }

    @FXML
    private void initialize() {
        formErrorListeningService.setupFieldListener(txfUsernameOrEmail, lblInvalidCredentials);
        formErrorListeningService.setupFieldListener(pwfPassword, lblInvalidCredentials);
    }

    @FXML
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onActionHplRedirectToChangePassword() {
        navigationService.navigateTo("change-password1-email.fxml");
    }

    @FXML
    public void onActionBtnLogin() {
        String usernameOrEmail = txfUsernameOrEmail.getText().trim();
        String password = pwfPassword.getText().trim();

        if(isEmail(usernameOrEmail)) {
            userService.setCurrentPlayer(playerDAO.findByEmail(usernameOrEmail));
        } else {
            userService.setCurrentPlayer(playerDAO.findByUsername(usernameOrEmail));
        }

        if(userService.getCurrentPlayer() == null 
            || 
        !userService.check(password, userService.getCurrentPlayer().getPassword())) {
            
            formErrorListeningService.showErrors(lblInvalidCredentials, txfUsernameOrEmail);
            formErrorListeningService.showErrors(lblInvalidCredentials, pwfPassword);
        } else {
            userService.setLoggedIn(true);
            navigationService.navigateTo("title-screen.fxml");
        }
    }

    private boolean isEmail(String input) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return input.matches(emailRegex);
    }

    @FXML
    public void onActionHplRedirectToRegisterPlayer() {
        navigationService.navigateTo("register-player.fxml");
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
