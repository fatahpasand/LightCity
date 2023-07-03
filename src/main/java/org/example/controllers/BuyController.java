package org.example.controllers;

import org.example.Database;
import org.example.Game;
import org.example.Main;
import org.example.defualtSystem.Bank;
import org.example.defualtSystem.Life;
import org.example.defualtSystem.Municipality;
import org.example.models.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Label gameStat;
    @FXML
    private ListView<String> list;
    @FXML
    private TextField getID;
    @FXML
    private Button buyButton;
    @FXML
    private Button backButton;
    @FXML
    private ToolBar bar;

    public void backButton(ActionEvent event) throws SQLException, IOException {
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

    public void buyButton(ActionEvent event) throws SQLException, IOException {
        Game game = new Game();
        game.isAlive(event);
        String land_id = getID.getText();
        Municipality mun = new Municipality();
        if (!mun.buyLand(land_id)){
            label.setText("Poor");
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/buy-view.fxml"));
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

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bank bank = new Bank();
        Food food = new Food();
        Life life = new Life();
        Game game = new Game();

        try {
            gameStat.setText("Balance: " + bank.money() +
                    "Br | Food: " + food.food() +
                    "/7 | Sleep = " + life.sleepStat() +
                    " | Day Count = " + game.dayCount() +
                    " | Time = " + game.time() * 4 + ":00"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Database db = new Database();
        // Get all the lands in the municipality table
        ResultSet rs = null;
        try {
            rs = db.getData("Municipality", "*");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Create an array list to hold the lands
        ArrayList<String> lands = new ArrayList<>();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (!rs.getBoolean("Owned")) {
                    lands.add(rs.getString("Land_id") + ", " + rs.getString("Price"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        list.getItems().addAll(lands);
    }
}

