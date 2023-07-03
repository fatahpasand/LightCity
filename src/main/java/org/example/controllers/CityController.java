package org.example.controllers;

import org.example.Game;
import org.example.Main;
import org.example.defualtSystem.Bank;
import org.example.defualtSystem.Life;
import org.example.models.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CityController {
    @FXML
    private Label label;
    @FXML
    private Label gameStat;
    @FXML
    private Button municipalityButton;
    @FXML
    private Button companyButton;
    @FXML
    private Button bankButton;
    @FXML
    private Button fastFoodButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button exitButton;
    @FXML
    private ToolBar bar;


    public void municipalityButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/municipality-view.fxml"));
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
    public void companyButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/company-view.fxml"));
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
    public void bankButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/bank-view.fxml"));
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
    public void fastFoodButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/shop-view.fxml"));
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
    public void homeButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/home-view.fxml"));
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
    public void exitButton(ActionEvent event) throws SQLException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void initialize() throws SQLException {
        Bank bank = new Bank();
        Food food = new Food();
        Life life = new Life();
        Game game = new Game();

        gameStat.setText("Balance: " + bank.money() +
                "Br | Food: " + food.food() +
                "/7 | Sleep = " + life.sleepStat() +
                " | Day Count = " + game.dayCount() +
                " | Time = " + game.time()*4 + ":00"
                );
    }
}
