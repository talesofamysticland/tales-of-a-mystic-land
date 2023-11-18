package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import org.talesof.talesofamysticland.injection.DependencyInjector;
import org.talesof.talesofamysticland.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class SettingsController {

    private UserService userService;

    @FXML
    private BorderPane root;

    public SettingsController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void onActionOpenControlScreen(ActionEvent event) throws IOException {
        root.setCenter(DependencyInjector.load("settings-controls.fxml"));
    }

    @FXML
    public void onActionOpenAudioScreen() throws IOException {
        root.setCenter(DependencyInjector.load("settings-audio.fxml"));
    }

    @FXML
    public void onActionOpenResolutionScreen() throws IOException {
        root.setCenter(DependencyInjector.load("settings-resolution.fxml"));
    }

    @FXML
    public void onClickSelectResolutionLower() {

    }

    @FXML
    public void onClickSelectResolutionStandart() {

    }

    @FXML
    public void onClickSelectResolutionHighest() {

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

    @FXML
    public void onActionExitGame() {

    }
}
