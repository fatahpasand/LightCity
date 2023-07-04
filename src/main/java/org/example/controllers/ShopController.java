package org.example.controllers;

import org.example.Game;
import org.example.Main;
import org.example.defualtSystem.Bank;
import org.example.defualtSystem.FastFoodShop;
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

public class ShopController {
    @FXML
    private Label label;
    @FXML
    private Label gameStat;
    @FXML
    private Button hotdogButton;
    @FXML
    private Button hamburgerButton;
    @FXML
    private Button pizzaButton;
    @FXML
    private Button cityButton;
    @FXML
    private ToolBar bar;

    FastFoodShop fsh = new FastFoodShop();
    Food food = new Food();
    public void hotdogButton() throws SQLException {
        if(food.food() >= 10){
            label.setText("Wasting money!!!");
        }else {
            if (fsh.hotdog()) {
                initialize();
            } else {
                label.setText("Poor!!!");
            }
        }
    }
    public void hamburgerButton()throws SQLException {
        if(food.food() >= 10){
            label.setText("Wasting money!!!");
        }else {
            if (fsh.hamburger()) {
                initialize();
            } else {
                label.setText("Poor!!!");
            }
        }
    }
    public void pizzaButton()throws SQLException {
        if(food.food() >= 10){
            label.setText("Wasting money!!!");
        }else {
            if (fsh.pizza()) {
                initialize();
            } else {
                label.setText("Poor!!!");
            }
        }
    }
    public void cityButton(ActionEvent event) throws SQLException, IOException {Game game = new Game();
        game.isAlive(event, "city");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/city-view.fxml"));
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
    @FXML
    public void initialize() throws SQLException {
        Bank bank = new Bank();
        Food food = new Food();
        Life life = new Life();
        Game game = new Game();

        gameStat.setText("Balance: " + bank.money() +
                "Br | Food: " + food.food() +
                "/10 | Sleep = " + life.sleepStat() +
                " | Day Count = " + game.dayCount() +
                " | Time = " + game.time()*4 + ":00"
        );
    }
}
