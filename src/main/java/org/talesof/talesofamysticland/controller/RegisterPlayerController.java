package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.dao.PlayerDAO;
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
    private Label lblPasswordTooShort;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private Label lblDifferentPasswords;


    public RegisterPlayerController(UserService userService, NavigationService navigationService, PlayerDAO playerDAO) {
        this.userService = userService;
        this.navigationService = navigationService;
        this.playerDAO = playerDAO;
    }

    @FXML
    public void initialize() {
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
    public void onActionBtnRegisterPlayer() {

        String username = txfUsername.getText().trim();
        String email = txfEmail.getText().trim();
        String password = pwfPassword.getText().trim();
        String confirmedPassword = pwfConfirmedPassword.getText().trim();

        if(isUsernameValid(username) && isEmailValid(email) && isPasswordValid(password, confirmedPassword)) {
            navigationService.navigateTo("title-screen.fxml");
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

        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            lblInvalidEmail.setVisible(true);
            lblInvalidEmail.setManaged(true);
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

    @FXML
    public void onActionHplRedirectToLogin() {
        navigationService.navigateTo("login.fxml");
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
