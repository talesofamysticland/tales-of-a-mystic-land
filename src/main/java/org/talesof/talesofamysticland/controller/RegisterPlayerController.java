package org.talesof.talesofamysticland.controller;

import java.sql.SQLException;

import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.model.Player;
import org.talesof.talesofamysticland.service.FormErrorListeningService;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class RegisterPlayerController {

    private UserService userService;
    private NavigationService navigationService;
    private FormErrorListeningService formErrorListeningService;

    private PlayerDAO playerDAO;

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

    @FXML
    private TextField txfVerificationToken;

    @FXML
    private Label lblUsernameAlreadyExists;

    @FXML
    private Label lblBlankUsername;

    @FXML
    private Label lblUsernameSpecialCharacters;

    @FXML
    private Label lblUsernameTooBig;

    @FXML
    private Label lblInvalidEmail;

    @FXML 
    private Label lblEmailAlreadyExists;

    @FXML
    private Label lblPasswordTooShort;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private Label lblDifferentPasswords;

    @FXML
    private Label lblBlankToken;

    @FXML
    private Label lblInvalidToken;

    public RegisterPlayerController(
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
    public void initialize() {
        if(userService.isLoggedIn()) {
            navigationService.navigateTo("title-screen.fxml");
        }

        if(txfUsername != null 
            && 
        txfEmail != null 
            && 
        pwfPassword != null 
            && 
        pwfConfirmedPassword != null) {
            setupFormFieldListeners();
        }

        if(txfVerificationToken != null) {
            formErrorListeningService.setupFieldListener(txfVerificationToken, lblBlankToken, lblInvalidToken);
        }
        
    }

    private void setupFormFieldListeners() {
        formErrorListeningService.setupFieldListener(
            txfUsername, lblBlankUsername, lblUsernameTooBig, lblUsernameSpecialCharacters, lblUsernameAlreadyExists
        );
        formErrorListeningService.setupFieldListener(txfEmail, lblInvalidEmail);
        formErrorListeningService.setupFieldListener(pwfPassword, lblPasswordTooShort, lblInvalidPassword);
        formErrorListeningService.setupFieldListener(pwfConfirmedPassword, lblDifferentPasswords);
    }

    @FXML
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onActionBtnRegisterPlayer() throws SQLException {

        String username = txfUsername.getText().trim();
        String email = txfEmail.getText().trim();
        String password = pwfPassword.getText().trim();
        String confirmedPassword = pwfConfirmedPassword.getText().trim();

        if(isUsernameValid(username) && isEmailValid(email) && isPasswordValid(password, confirmedPassword)) {
            userService.setCurrentPlayer(new Player());
            userService.getCurrentPlayer().setUsername(username);
            userService.getCurrentPlayer().setEmail(email);
            userService.getCurrentPlayer().setPassword(userService.hash(password));
            playerDAO.save(userService.getCurrentPlayer());

            System.out.println(playerDAO.findByUsername(username));

            navigationService.navigateTo("register-player-verification-token.fxml");
        }
    }

    private boolean isUsernameValid(String username) {

        boolean usernameIsValid = true;

        if(username.isBlank()) {
            formErrorListeningService.showErrors(lblBlankUsername, txfUsername);
            usernameIsValid = false;
        }

        if(username.length() > 20) {
            formErrorListeningService.showErrors(lblUsernameTooBig, txfUsername);
            usernameIsValid = false;
        }

        if(username.matches(".*[^a-zA-Z0-9].*")) {
            formErrorListeningService.showErrors(lblUsernameSpecialCharacters, txfUsername);
            usernameIsValid = false;
        }

        if(playerDAO.findByUsername(username) != null) {
            formErrorListeningService.showErrors(lblUsernameAlreadyExists, txfUsername);
            usernameIsValid = false;
        }

        return usernameIsValid;
    }

    private boolean isEmailValid(String email) {
        boolean emailIsValid = true;

        if(!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            formErrorListeningService.showErrors(lblInvalidEmail, txfEmail);
            emailIsValid = false;
        }

        if(playerDAO.findByEmail(email) != null) {
            formErrorListeningService.showErrors(lblEmailAlreadyExists, txfEmail);
            emailIsValid = false;
        }

        return emailIsValid;
    }

    public boolean isPasswordValid(String password, String confirmedPassword) {

        boolean passwordIsValid = true;

        if(password.length() < 7) {
            formErrorListeningService.showErrors(lblPasswordTooShort, pwfPassword);
            passwordIsValid = false;
        }

        final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).+$";
        
        if (!password.matches(REGEX)) {
            formErrorListeningService.showErrors(lblInvalidPassword, pwfPassword);
            passwordIsValid = false;
        }

        if(!password.equals(confirmedPassword)) {
            formErrorListeningService.showErrors(lblDifferentPasswords, pwfConfirmedPassword);
            passwordIsValid = false;
        }

        return passwordIsValid;
    }

    public boolean isVerificationTokenValid(String verificationToken) {

        boolean verificationTokenIsValid = true;

        if(verificationToken.isBlank()) {
            formErrorListeningService.showErrors(lblBlankToken, txfVerificationToken);
            verificationTokenIsValid = false;
        }

        if(!verificationToken.equals(userService.getCurrentPlayer().getVerificationToken())) {
            formErrorListeningService.showErrors(lblInvalidToken, txfVerificationToken);
            verificationTokenIsValid = false;
        }

        return verificationTokenIsValid;
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        navigationService.navigateTo("login.fxml");
    }

    @FXML
    public void onActionBtnValidateToken() throws SQLException {
        String verificationToken = txfVerificationToken.getText().trim();

        if(verificationToken.equals(userService.getCurrentPlayer().getVerificationToken())) {

            if(userService.getCurrentPlayer() != null) {
                userService.getCurrentPlayer().setVerified(true);
                playerDAO.update(userService.getCurrentPlayer());

                userService.setLoggedIn(true);

                navigationService.navigateTo("title-screen.fxml");
            }
        }
    }

    @FXML
    public void onActionHplResendToken() {

    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
