package org.talesof.talesofamysticland;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        FXMLLoader fxmlLoader = new FXMLLoader(TalesOfAMysticLandApplication.class.getResource("view/save-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Tales of a Mystic Land");
        stage.setResizable(true);
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
