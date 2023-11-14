package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SettingsController {
    @FXML
    public void onActionOpenControlScreen(ActionEvent event) throws IOException {
        String fxmlPath = "/org/talesof/talesofamysticland/view/settings-controls.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onActionOpenAudioScreen(){
        //TODO
    }

    @FXML
    public void onActionOpenResolutionScreen(){
        //TODO
    }

    @FXML
    public void onActionSaveSettings(){
        //TODO
    }

    @FXML
    public void onActionCancelSettings(){
        //TODO
    }

}
