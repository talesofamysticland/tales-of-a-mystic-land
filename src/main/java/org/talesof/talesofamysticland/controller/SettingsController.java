package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SettingsController {

    private UserService userService;
    private NavigationService navigationService;

    public SettingsController(UserService userService, NavigationService navigationService) {
        this.userService = userService;
        this.navigationService = navigationService;
    }

    @FXML
    public void onActionOpenControlScreen(ActionEvent event) throws IOException {
        navigationService.navigateTo("settings-controls.fxml");
    }

    @FXML
    public void onActionOpenAudioScreen(){
        navigationService.navigateTo("settings-audio.fxml");
    }

    @FXML
    public void onActionOpenResolutionScreen(){
        navigationService.navigateTo("settings-screen.fxml");
    }

    @FXML
    public void onActionSaveSettings(){
        //TODO
    }

    @FXML
    public void onActionCancelSettings(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
