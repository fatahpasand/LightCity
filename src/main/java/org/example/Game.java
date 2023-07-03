package org.example;

import org.example.defualtSystem.Bank;
import org.example.defualtSystem.Life;
import org.example.defualtSystem.Municipality;
import org.example.interfaces.GameInterface;
import org.example.models.Food;
import org.example.models.Job;
import org.example.models.User;
import org.example.models.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Game implements GameInterface {

    @Override
    public void continueGame(User user) {
    }

    @Override
    public void startGame(User user) throws SQLException {
        Character character = new Character(user);
        character.add(user);
        Municipality municipality = new Municipality();
        municipality.add(user);
        Bank bank = new Bank();
        bank.add(user);
        Game game = new Game();
        game.add(user);
        Job job = new Job();
        job.add(user);
    }
    public void add(User user){
        Connection conn = null;
        String dbPath = "src/main/resources/org/example/database/" + user.getUsername() + ".db";

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

        try {
            // Connect to the database using the specified path
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Game (Time, Day_count) VALUES (?, ?)");
            stmt.setInt(1, 2);
            stmt.setInt(2, 0);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public int time() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Game", "Time");
        int balance = 0;
        while (rs.next()) {
            balance = rs.getInt("Time");
        }
        return balance;
    }
    public void timePassed() throws SQLException{
        Database db  = new Database();
        String query = "UPDATE Game SET Time = ?";
        if(time()+1 > 6) {
            db.setData("UPDATE Game SET Day_count = ?", dayCount() + 1);
            db.setData(query, time() - 5);
        }else{
            db.setData(query, time() + 1);
        }
        Food food = new Food();
        query = "UPDATE Character SET Food = ?";
        db.setData(query,food.food()-1);
        query = "UPDATE Character SET Sleep = ?";
        Life life = new Life();
        db.setData(query,life.sleepStat()-1);
    }
    public int dayCount() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Game", "Day_count");
        int balance = 0;
        while (rs.next()) {
            balance = rs.getInt("Day_count");
        }
        return balance;
    }
    public void isAlive(ActionEvent event) throws SQLException, IOException {
        Life life = new Life();
        if (!life.life()) {
            Database db = new Database();
            db.deleteDatabase();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes/death-view.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Image backgroundImage = new Image("file:src/main/resources/org/example/background.jpg");
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
            BackgroundImage background = new BackgroundImage(backgroundImage,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, backgroundSize);
            root.setBackground(new Background(background));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Signup Menu");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
}

