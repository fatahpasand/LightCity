package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.Database;
import org.example.Main;
import org.example.models.User;
import org.example.models.Character;

import java.io.IOException;
import java.sql.SQLException;

public class SigninMenuController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField getUsername;
    @FXML
    private PasswordField getPassword;
    @FXML
    private Button signinButton;
    @FXML
    private Button backButton;

    public void signinButton(ActionEvent event) throws SQLException, IOException {
        User user = new User(getUsername.getText(),getPassword.getText());
        if(user.signIn()) {
            Database.setdbname(getUsername.getText());
            Character character = new Character(user);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(character.position()));
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
        }else {
            welcomeText.setText("Incorrect credentials");
        }
    }
    public void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/starting-menu-view.fxml"));
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
}
