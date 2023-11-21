package org.talesof.talesofamysticland;

import java.io.IOException;

import org.talesof.talesofamysticland.controller.ChangePasswordController;
import org.talesof.talesofamysticland.controller.CharacterCreationController;
import org.talesof.talesofamysticland.controller.LoginController;
import org.talesof.talesofamysticland.controller.RegisterPlayerController;
import org.talesof.talesofamysticland.controller.SaveSelectionController;
import org.talesof.talesofamysticland.controller.SettingsController;
import org.talesof.talesofamysticland.controller.TitleScreenController;
import org.talesof.talesofamysticland.dao.ChangePasswordDAO;
import org.talesof.talesofamysticland.dao.PlayerDAO;
import org.talesof.talesofamysticland.dao.SaveDAO;
import org.talesof.talesofamysticland.database.DatabaseManager;
import org.talesof.talesofamysticland.injection.DependencyInjector;
import org.talesof.talesofamysticland.service.EmailService;
import org.talesof.talesofamysticland.service.FormErrorListeningService;
import org.talesof.talesofamysticland.service.NavigationService;
import org.talesof.talesofamysticland.service.UserService;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TalesOfAMysticLandApplication extends Application {

    private Stage stage;

    public final static int originalTileSize = 16;  // 16x16
    public final static int scale = 5;

    public final static int tileSize = originalTileSize * scale;
    public final static int maxScreenCol = 16; 
    public final static int maxScreenRow = 10; 
    public final static int screenWidth = tileSize * maxScreenCol;  
    public final static int screenHeight = tileSize * maxScreenRow;

    @Override
    public void start(Stage primaryStage) throws IOException {
        setupDatabase();

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

    private void setupDatabase() {
        //DatabaseManager.dropTables();
        DatabaseManager.createTables();
        DatabaseManager.createViews();
        DatabaseManager.insertData();
    }

    private void setUpDependencyInjector() {

        // Services
        NavigationService navigationService = new NavigationService(stage);
        UserService userService = new UserService();
        EmailService emailService = new EmailService();
        FormErrorListeningService formErrorListeningService = new FormErrorListeningService();

        // DAOs
        PlayerDAO playerDAO = new PlayerDAO();
        ChangePasswordDAO changePasswordDAO = new ChangePasswordDAO();
        SaveDAO saveDAO = new SaveDAO();

        Callback<Class<?>, Object> changePasswordControllerFactory = param -> {
                return new ChangePasswordController(
                        userService, 
                        navigationService, 
                        formErrorListeningService, 
                        emailService, 
                        playerDAO, 
                        changePasswordDAO
                );
        }; 

        Callback<Class<?>, Object> characterCreationControllerFactory = param -> {
                return new CharacterCreationController(userService, navigationService, formErrorListeningService, saveDAO);
        };

        Callback<Class<?>, Object> loginControllerFactory = param -> {
                return new LoginController(userService, navigationService, formErrorListeningService, playerDAO);
        };

        Callback<Class<?>, Object> registerPlayerControllerFactory = param -> {
                return new RegisterPlayerController(
                        userService, 
                        navigationService, 
                        formErrorListeningService, 
                        emailService, 
                        playerDAO
                );
        };

        Callback<Class<?>, Object> saveSelectionControllerFactory = param -> {
                return new SaveSelectionController(userService, navigationService, saveDAO, playerDAO);
        };

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
