package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TitleScreenController {

    private UserService userService;
    private NavigationService navigationService;

    @FXML
    private BorderPane root;

    public TitleScreenController(UserService userService, NavigationService navigationService) {
        this.userService = userService;
        this.navigationService = navigationService;
    }
    
    @FXML 
    public void onClickImgOpenConfigurations() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(TitleScreenController.class.getResource("/org/talesof/talesofamysticland/view/settings-main.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(700);
        stage.setHeight(600);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onActionBtnPlay() {
        String fxmlFile = "save-selection.fxml";

        if(!userService.isLoggedIn()) { fxmlFile = "login.fxml"; } 

        navigationService.navigateTo(root, fxmlFile);
    }


    @FXML
    public void onActionBtnExit() {
        // TODO
    }

    @FXML
    public void onActionHplRedirectToRegisterPlayer() {
        navigationService.navigateTo(root, "register-player.fxml");
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        navigationService.navigateTo(root, "login.fxml");
    }
}
