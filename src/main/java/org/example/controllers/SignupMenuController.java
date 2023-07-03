package org.example.controllers;

import org.example.Database;
import org.example.Game;
import org.example.Main;
import org.example.models.User;

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

public class SignupMenuController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField getUsername;
    @FXML
    private PasswordField getPassword;
    @FXML
    private Button signupButton;
    @FXML
    private Button backButton;


    public void signupButton(ActionEvent event) throws SQLException, IOException {
        Database db = new Database();
        Database.setdbname(getUsername.getText());
        if(db.createNewDatabase()) {
            User user = new User(getUsername.getText(),getPassword.getText());
            db.createTables();
            user.add();
            Game game = new Game();
            game.startGame(user);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/instruction-view.fxml"));
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
            welcomeText.setText("Duplicate username");
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