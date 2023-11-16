package org.talesof.talesofamysticland.controller;

import java.io.IOException;

import org.talesof.talesofamysticland.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TitleScreenController {

    private UserService userService;

    @FXML
    private BorderPane root;

    public TitleScreenController(UserService userService) {
        this.userService = userService;
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
        String fxmlFile = "save-selection";

        if(!userService.isLoggedIn()) { fxmlFile = "login"; } 

        redirectTo(fxmlFile);
    }


    @FXML
    public void onActionBtnExit() {
        // TODO
    }

    @FXML
    public void onActionHplRedirectToRegisterPlayer() {
        redirectTo("register-player");
    }

    @FXML
    public void onActionHplRedirectToLogin() {
        redirectTo("login");
    }

    private void redirectTo(String fxmlFile) {
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/org/talesof/talesofamysticland/view/" + fxmlFile + ".fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
