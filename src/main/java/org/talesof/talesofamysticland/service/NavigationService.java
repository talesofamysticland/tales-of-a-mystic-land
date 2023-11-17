package org.talesof.talesofamysticland.service;

import java.io.IOException;
import java.util.Stack;

import org.talesof.talesofamysticland.injection.DependencyInjector;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationService {

    private Stack<Scene> history = new Stack<>();
    private Stage primaryStage;

    public NavigationService(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void navigateTo(String viewName) {
        try {
            Parent root = DependencyInjector.load(viewName);
            Scene scene = new Scene(root);
            history.push(primaryStage.getScene());

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateBack() {
        if (!history.isEmpty()) {
            Scene previousScene = history.pop();
            primaryStage.setScene(previousScene);
            primaryStage.show();
        }
    }
}
