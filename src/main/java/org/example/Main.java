package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/starting-menu-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Image backgroundImage = new Image("file:src/main/resources/org/example/background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backgroundSize);
        root.setBackground(new Background(background));
        Image icon = new Image("file:src/main/resources/org/example/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Growing in Lightcity");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}
