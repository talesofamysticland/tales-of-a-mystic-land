package org.talesof.talesofamysticland.service;

import java.io.IOException;
import java.util.Stack;

import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.entity.PlayerCharacter;
import org.talesof.talesofamysticland.injection.DependencyInjector;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NavigationService {

    private Stack<Scene> history = new Stack<>();
    private Stage stage;

    public NavigationService(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void navigateTo(String viewName) {
        try {
            Parent root = DependencyInjector.load(viewName);
            Scene scene = new Scene(root);
            history.push(stage.getScene());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateBack() {
        if (!history.isEmpty()) {
            Scene previousScene = history.pop();
            stage.setScene(previousScene);
            stage.show();
        }
    }

    public void openConfigurationsMenu() {
        try {
            Parent root = DependencyInjector.load("settings-main.fxml");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setWidth(700);
            stage.setHeight(600);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame(GameService gameService) {
        Game.start(gameService);
    }

    public void closeApplication() {
        stage.close();
    }
}
