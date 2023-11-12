package org.talesof.talesofamysticland;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TalesOfAMysticLandApplication extends Application {

    final int originalTileSize = 16;  // 16x16
    final int scale = 5;

    public final int tileSize = originalTileSize * scale;  // 48x48
    public final int maxScreenCol = 16; //5x4, 16x9, 21x9
    public final int maxScreenRow = 9; //5x4, 16x9, 21x9
    public final int screenWidth = tileSize * maxScreenCol;  // 960px
    public final int screenHeight = tileSize * maxScreenRow;  // 576px

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(TalesOfAMysticLandApplication.class.getResource("view/title-screen.fxml"));
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
