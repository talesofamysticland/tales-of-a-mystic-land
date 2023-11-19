package org.talesof.talesofamysticland;

import java.io.IOException;

import org.talesof.talesofamysticland.controller.ChangePasswordController;
import org.talesof.talesofamysticland.controller.CharacterCreationController;
import org.talesof.talesofamysticland.controller.LoginController;
import org.talesof.talesofamysticland.controller.RegisterPlayerController;
import org.talesof.talesofamysticland.controller.SaveSelectionController;
import org.talesof.talesofamysticland.controller.SettingsController;
import org.talesof.talesofamysticland.controller.TitleScreenController;
import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.database.TableManager;
import org.talesof.talesofamysticland.injection.DependencyInjector;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TalesOfAMysticLandApplication extends Application {

    private Stage stage;

    final int originalTileSize = 16;
    final int scale = 5;

    public final int tileSize = originalTileSize * scale;  
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 10; 
    public final int screenWidth = tileSize * maxScreenCol;  
    public final int screenHeight = tileSize * maxScreenRow;  

    @Override
    public void start(Stage primaryStage) throws IOException {
        TableManager.drop();
        TableManager.create();

        stage = primaryStage;
        
        setUpDependencyInjector();

        Parent root = DependencyInjector.load("title-screen.fxml");
        Scene scene = new Scene(root);
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);
        stage.setScene(scene);
        stage.setTitle("Tales of a Mystic Land");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setUpDependencyInjector() {

        NavigationService navigationService = new NavigationService(stage);
        UserService userService = new UserService();
        PlayerDAO playerDAO = new PlayerDAO();

        Callback<Class<?>, Object> changePasswordControllerFactory = param -> new ChangePasswordController(userService, navigationService);

        Callback<Class<?>, Object> characterCreationControllerFactory = param -> new CharacterCreationController(navigationService);

        Callback<Class<?>, Object> loginControllerFactory = param -> new LoginController(userService, navigationService);

        Callback<Class<?>, Object> registerPlayerControllerFactory = param -> new RegisterPlayerController(userService, navigationService, playerDAO);

        Callback<Class<?>, Object> saveSelectionControllerFactory = param -> new SaveSelectionController(navigationService);

        Callback<Class<?>, Object> settingsControllerFactory = param -> new SettingsController(userService);

        Callback<Class<?>, Object> titleScreenControllerFactory = param -> new TitleScreenController(userService, navigationService);

        DependencyInjector.addInjectionMethod(
                ChangePasswordController.class, changePasswordControllerFactory
        );

        DependencyInjector.addInjectionMethod(
                CharacterCreationController.class, characterCreationControllerFactory
        );

        DependencyInjector.addInjectionMethod(
                LoginController.class, loginControllerFactory
        );

        DependencyInjector.addInjectionMethod(
                RegisterPlayerController.class, registerPlayerControllerFactory
        );

        DependencyInjector.addInjectionMethod(
                SaveSelectionController.class, saveSelectionControllerFactory
        );

        DependencyInjector.addInjectionMethod(
                SettingsController.class, settingsControllerFactory
        );

        DependencyInjector.addInjectionMethod(
                TitleScreenController.class, titleScreenControllerFactory
        );
    }
}
