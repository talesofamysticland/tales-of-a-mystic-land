package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import org.talesof.talesofamysticland.injection.DependencyInjector;
import org.talesof.talesofamysticland.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class SettingsController {

    private UserService userService;

    @FXML
    private BorderPane root;

    public SettingsController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void onActionOpenControlScreen() throws IOException {
        loadAndSetToCenter("settings-controls.fxml");
    }

    @FXML
    public void onActionOpenAudioScreen() throws IOException {
        loadAndSetToCenter("settings-audio.fxml");
    }

    @FXML
    public void onActionOpenResolutionScreen() throws IOException {
        loadAndSetToCenter("settings-screen.fxml");
    }

    private void loadAndSetToCenter(String fxmlFileName) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        Parent root = DependencyInjector.load(fxmlFileName);
        stage.getScene().setRoot(root);
        setCenterContent(root);
    }

    private void setCenterContent(Node content) {
        BorderPane.setAlignment(content, Pos.CENTER);
        this.root.setCenter(content);
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
