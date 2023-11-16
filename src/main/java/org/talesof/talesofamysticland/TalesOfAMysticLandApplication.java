package org.talesof.talesofamysticland;

import java.io.IOException;

import org.talesof.talesofamysticland.controller.TitleScreenController;
import org.talesof.talesofamysticland.injection.DependencyInjector;
import org.talesof.talesofamysticland.service.UserService;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TalesOfAMysticLandApplication extends Application {

    final int originalTileSize = 16;
    final int scale = 5;

    public final int tileSize = originalTileSize * scale;  
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 9; 
    public final int screenWidth = tileSize * maxScreenCol;  
    public final int screenHeight = tileSize * maxScreenRow;  

    @Override
    public void start(Stage stage) throws IOException {
        setUpDependecyInjector();

        Parent root = DependencyInjector.load("view/title-screen.fxml");
        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setTitle("Tales of a Mystic Land");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setUpDependecyInjector() {

        Callback<Class<?>, Object> controllerFactory = param -> {
            UserService userService = new UserService();
            return new TitleScreenController(userService);
        };

        DependencyInjector.addInjectionMethod(
                TitleScreenController.class, controllerFactory
        );
    }
}
