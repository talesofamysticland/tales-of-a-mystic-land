package org.talesof.talesofamysticland.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.talesof.talesofamysticland.dao.ChangePasswordDAO;
import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.model.ChangePassword;
import org.talesof.talesofamysticland.model.Player;
import org.talesof.talesofamysticland.service.EmailService;
import org.talesof.talesofamysticland.service.FormErrorListeningService;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChangePasswordController {

    private UserService userService;
    private NavigationService navigationService;
    private FormErrorListeningService formErrorListeningService;
    private EmailService emailService;

    private PlayerDAO playerDAO;
    private ChangePasswordDAO changePasswordDAO;

    @FXML
    private TextField txfEmail;

    @FXML
    private TextField txfVerificationToken;

    @FXML
    private PasswordField pwfNewPassword;

    @FXML
    private PasswordField pwfConfirmedNewPassword;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblPlayerNotRegistered;

    @FXML
    private Label lblInvalidToken;

    @FXML
    private Label lblTokenExpired;

    @FXML
    private Label lblNewPasswordTooShort;

    @FXML
    private Label lblInvalidNewPassword;

    @FXML
    private Label lblInvalidCurrentPassword;

    @FXML
    private Label lblDifferentNewPasswords;

    public ChangePasswordController(
        UserService userService, 
        NavigationService navigationService, 
        FormErrorListeningService formErrorListeningService, 
        EmailService emailService, 
        PlayerDAO playerDAO,
        ChangePasswordDAO changePasswordDAO) {
            
        this.userService = userService;
        this.navigationService = navigationService;
        this.formErrorListeningService = formErrorListeningService;
        this.emailService = emailService;
        this.playerDAO = playerDAO;
        this.changePasswordDAO = changePasswordDAO;
    }

    @FXML
    public void initialize() {

        if(txfEmail != null) {
            userService.setChangePasswordRequest(null);
            formErrorListeningService.setupFieldListener(txfEmail, lblInvalidEmail, lblPlayerNotRegistered);
        }

        if(txfVerificationToken != null) {
            formErrorListeningService.setupFieldListener(txfVerificationToken, lblInvalidToken, lblTokenExpired);
        }
        
        if(pwfNewPassword != null && pwfConfirmedNewPassword != null) {
            formErrorListeningService.setupFieldListener(
                pwfNewPassword, 
                lblDifferentNewPasswords, 
                lblNewPasswordTooShort, 
                lblInvalidNewPassword, 
                lblInvalidCurrentPassword
            );
            formErrorListeningService.setupFieldListener(pwfConfirmedNewPassword, lblDifferentNewPasswords);
        }
    }

    @FXML
    public void onClickImgOpenConfigurations() {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onActionBtnSendToken() {
        String email = txfEmail.getText().trim();

        if(isEmailValid(email)) {
            Integer playerId = playerDAO.findByEmail(email).getId();
            userService.setChangePasswordRequest(new ChangePassword(playerId));

            try {
                changePasswordDAO.save(userService.getChangePasswordRequest());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sendVerificationToken(email, userService.getChangePasswordRequest().getVerificationToken());

            userService.getChangePasswordRequest().setExpirationDate(LocalDateTime.now().plusMinutes(7));
            
            navigationService.navigateTo("change-password2-token.fxml");
        }
    }

    private boolean isEmailValid(String email) {
        boolean isEmailValid = true;

        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            formErrorListeningService.showErrors(lblInvalidEmail, txfEmail);
            isEmailValid = false;
        }

        if(playerDAO.findByEmail(email) == null) {
            formErrorListeningService.showErrors(lblPlayerNotRegistered, txfEmail);
            isEmailValid = false;
        }
        
        return isEmailValid;
    }

    private void sendVerificationToken(String email, String verificationToken) {
        emailService.send(
            email, 
            "Cuidado ao usar poções de esquecimento outra vez!", 
            "<p>Aqui está o código de segurança para mudar sua senha: " 
            + "<b>" + verificationToken + "</p></b>"
            + "<p>Se você não solicitou esta mensagem, por favor, desconsidere este e-mail.</p>"
            + "<br><br>"
            + "<p>Tales of a Mystic Land, Inc.</p>"
        );
    }

    @FXML
    public void onActionBtnValidateToken() {
        String verificationToken = txfVerificationToken.getText().trim();
        
        if(isVerificationTokenValid(verificationToken)) {

            userService.getChangePasswordRequest().setValidated(true);
            userService.getChangePasswordRequest().setVerificationDate(LocalDateTime.now());

            try {
                changePasswordDAO.update(userService.getChangePasswordRequest());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            navigationService.navigateTo("change-password3-new-password.fxml");
        }
    }

    private boolean isVerificationTokenValid(String verificationToken) {
        boolean isVerificationTokenValid = true;

        if(userService.getChangePasswordRequest().getExpirationDate().isBefore(LocalDateTime.now())) {
            formErrorListeningService.showErrors(lblTokenExpired, txfVerificationToken);
            userService.getChangePasswordRequest().setExpirationDate(LocalDateTime.now().plusMinutes(7));
            sendNewVerificationToken();
            isVerificationTokenValid = false;

        } else if(!verificationToken.equals(userService.getChangePasswordRequest().getVerificationToken())) {
            formErrorListeningService.showErrors(lblInvalidToken, txfVerificationToken);
            isVerificationTokenValid = false;
        }

        return isVerificationTokenValid;
    }

    private void sendNewVerificationToken() {
        userService.getChangePasswordRequest().generateVerificationToken();
        String newToken = userService.getChangePasswordRequest().getVerificationToken();
        sendVerificationToken(
            playerDAO.findById(userService.getChangePasswordRequest().getPlayerId()).getEmail(), 
            newToken
        );
    }

    @FXML
    public void onActionBtnSetNewPassword() {
        String newPassword = pwfNewPassword.getText().trim();
        String confirmedNewPassword = pwfConfirmedNewPassword.getText().trim();

        String oldPassword = playerDAO.findById(userService.getChangePasswordRequest().getPlayerId()).getPassword();

        if(isNewPasswordValid(oldPassword, newPassword, confirmedNewPassword)) {
            Integer playerId = userService.getChangePasswordRequest().getPlayerId();

            try {
                Player player = playerDAO.findById(playerId);
                player.setPassword(userService.hash(newPassword));
                playerDAO.update(player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            navigationService.navigateTo("login.fxml");
        }
    }

    private boolean isNewPasswordValid(String oldPassword, String newPassword, String confirmedNewPassword) {
        boolean isNewPasswordValid = true;

        if(newPassword.length() < 8) {
            formErrorListeningService.showErrors(lblNewPasswordTooShort, pwfNewPassword);
            isNewPasswordValid = false;
        }

        final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).+$";
        
        if (!newPassword.matches(REGEX)) {
            formErrorListeningService.showErrors(lblInvalidNewPassword, pwfNewPassword);
            isNewPasswordValid = false;
        }

        if(newPassword.equals(oldPassword)) {
            formErrorListeningService.showErrors(lblInvalidCurrentPassword, pwfNewPassword);
            isNewPasswordValid = false;
        }

        if(!newPassword.equals(confirmedNewPassword)) {
            formErrorListeningService.showErrors(lblDifferentNewPasswords, pwfNewPassword);
            formErrorListeningService.showErrors(lblDifferentNewPasswords, pwfConfirmedNewPassword);
            isNewPasswordValid = false;
        }

        return isNewPasswordValid;
    }

    @FXML
    public void onActionBtnBack() {
        navigationService.navigateBack();
    }
}
