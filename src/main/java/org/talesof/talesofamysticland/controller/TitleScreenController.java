package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TitleScreenController {

    private UserService userService;
    private NavigationService navigationService;

    @FXML
    private BorderPane root;

    @FXML
    private VBox boxAccountHyperlinks;

    @FXML
    private VBox boxLoggedAccount;

    @FXML
    private Label lblPlayerUsername;

    public TitleScreenController(UserService userService, NavigationService navigationService) {
        this.userService = userService;
        this.navigationService = navigationService;
    }

    @FXML
    public void initialize() {
        if(userService.isLoggedIn()) {
            lblPlayerUsername.setText(userService.getCurrentPlayer().getUsername());
            boxAccountHyperlinks.setVisible(false);
            boxLoggedAccount.setVisible(true);
        }
    }
    
    @FXML 
    public void onClickImgOpenConfigurations() throws IOException {
        navigationService.openConfigurationsMenu();
    }

    @FXML
    public void onActionBtnPlay() {
        String fxmlFile = "save-selection.fxml";

        if(!userService.isLoggedIn()) { fxmlFile = "login.fxml"; } 

        navigationService.navigateTo(fxmlFile);
    }


    @FXML
    public void onActionBtnExit() {
        navigationService.closeApplication();
    }

    @FXML
    public void onActionHplRedirectToRegisterPlayer() {
        navigationService.navigateTo("register-player.fxml");
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        navigationService.navigateTo("login.fxml");
    }

    @FXML
    private void onActionHplExitAccount() {
        userService.logout();
        navigationService.navigateTo("title-screen.fxml");
    }
}
