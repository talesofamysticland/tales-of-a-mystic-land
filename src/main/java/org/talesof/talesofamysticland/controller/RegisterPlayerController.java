package org.talesof.talesofamysticland.controller;

import java.sql.SQLException;

import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.model.Player;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class RegisterPlayerController {

    private UserService userService;
    private NavigationService navigationService;

    private PlayerDAO playerDAO;
    private Player player;

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

    public RegisterPlayerController(UserService userService, NavigationService navigationService, PlayerDAO playerDAO) {
        this.userService = userService;
        this.navigationService = navigationService;
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
            setupFieldListener(txfVerificationToken, lblBlankToken, lblInvalidToken);
        }
        
    }

    private void setupFormFieldListeners() {
        setupFieldListener(txfUsername, lblBlankUsername, lblUsernameTooBig, lblUsernameSpecialCharacters, lblUsernameAlreadyExists);
        setupFieldListener(txfEmail, lblInvalidEmail);
        setupFieldListener(pwfPassword, lblPasswordTooShort, lblInvalidPassword);
        setupFieldListener(pwfConfirmedPassword, lblDifferentPasswords);
    }

    private void setupFieldListener(TextField textField, Label... errorLabels) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            hideErrorLabels(errorLabels);
            clearFieldStyles(textField);
        });
    }

    private void hideErrorLabels(Label... errorLabels) {
        for (Label label : errorLabels) {
            label.setVisible(false);
            label.setManaged(false);
        }
    }

    private void clearFieldStyles(TextField textField) {
        textField.getStyleClass().removeAll("form__error-textfield");
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
            player = new Player();
            player.setUsername(username);
            player.setEmail(email);
            player.setPassword(userService.hash(password));
            playerDAO.save(player);

            System.out.println(playerDAO.findByUsername(username));

            navigationService.navigateTo("register-player-verification-token.fxml");
        }
    }

    private boolean isUsernameValid(String username) {

        boolean usernameIsValid = true;

        if(username.isBlank()) {
            lblBlankUsername.setVisible(true);
            lblBlankUsername.setManaged(true);
            txfUsername.getStyleClass().add("form__error-textfield");
            usernameIsValid = false;
        }

        if(username.length() > 20) {
            lblUsernameTooBig.setVisible(true);
            lblUsernameTooBig.setManaged(true);
            txfUsername.getStyleClass().add("form__error-textfield");
            usernameIsValid = false;
        }

        if(username.matches(".*[^a-zA-Z0-9].*")) {
            lblUsernameSpecialCharacters.setVisible(true);
            lblUsernameSpecialCharacters.setManaged(true);
            txfUsername.getStyleClass().add("form__error-textfield");
            usernameIsValid = false;
        }

        if(playerDAO.findByUsername(username) != null) {
            lblUsernameAlreadyExists.setVisible(true);
            lblUsernameAlreadyExists.setManaged(true);
            txfUsername.getStyleClass().add("form__error-textfield");
            usernameIsValid = false;
        }

        return usernameIsValid;
    }

    private boolean isEmailValid(String email) {
        boolean emailIsValid = true;

        if(!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            lblInvalidEmail.setVisible(true);
            lblInvalidEmail.setManaged(true);
            txfEmail.getStyleClass().add("form__error-textfield");
            emailIsValid = false;
        }

        if(playerDAO.findByEmail(email) != null) {
            lblEmailAlreadyExists.setVisible(true);
            lblEmailAlreadyExists.setManaged(true);
            txfEmail.getStyleClass().add("form__error-textfield");
            emailIsValid = false;
        }

        return emailIsValid;
    }

    public boolean isPasswordValid(String password, String confirmedPassword) {

        boolean passwordIsValid = true;

        if(password.length() < 7) {
            lblPasswordTooShort.setVisible(true);
            lblPasswordTooShort.setManaged(true);
            pwfPassword.getStyleClass().add("form__error-textfield");
            passwordIsValid = false;
        }

        if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$")) {
            lblInvalidPassword.setVisible(true);
            lblInvalidPassword.setManaged(true);
            pwfPassword.getStyleClass().add("form__error-textfield");
            passwordIsValid = false;
        }

        if(!password.equals(confirmedPassword)) {
            lblDifferentPasswords.setVisible(true);
            lblDifferentPasswords.setManaged(true);
            pwfConfirmedPassword.getStyleClass().add("form__error-textfield");
            passwordIsValid = false;
        }

        return passwordIsValid;
    }

    public boolean isVerificationTokenValid(String verificationToken) {

        boolean verificationTokenIsValid = true;

        if(verificationToken.isBlank()) {
            lblBlankToken.setVisible(true);
            lblBlankToken.setManaged(true);
            txfVerificationToken.getStyleClass().add("form__error-textfield");
            verificationTokenIsValid = false;
        }

        if(!verificationToken.equals("123456")) {
            lblInvalidToken.setVisible(true);
            lblInvalidToken.setManaged(true);
            txfVerificationToken.getStyleClass().add("form__error-textfield");
            verificationTokenIsValid = false;
        }

        return verificationTokenIsValid;
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        navigationService.navigateTo("login.fxml");
    }

    @FXML
    public void onActionBtnValidateToken() {
        String verificationToken = txfVerificationToken.getText().trim();

        if(verificationToken.equals("123456")) {
            navigationService.navigateTo("title-screen.fxml");
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
