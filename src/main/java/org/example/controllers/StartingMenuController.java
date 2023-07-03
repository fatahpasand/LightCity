package org.example.controllers;

import org.example.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StartingMenuController{
    @FXML
    private Label LightcityLabel;
    @FXML
    private Button ContinueButton;
    @FXML
    private Button NewGameButton;
    @FXML
    private Button ExitButton;

    public void ContinueButton(ActionEvent event) throws SQLException{
    }
    public void NewGameButton(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/signup-menu-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Image backgroundImage = new Image("file:src/main/resources/org/example/background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backgroundSize);
        root.setBackground(new Background(background));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void ExitButton(ActionEvent event) throws SQLException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}



