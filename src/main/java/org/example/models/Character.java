package org.example.models;


import org.example.Database;
import org.example.interfaces.CharacterInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Character implements CharacterInterface {
    User user;
    public Character() {
    }
    public Character(User user) {
        this.user = user;
    }

    @Override
    public void add(User user) throws SQLException {
        Connection conn = null;
        String dbPath = "src/main/resources/org/example/database/" + user.getUsername() + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Character " +
                    "(Food, Sleep, Position) VALUES (?, ?, ?)");
            stmt.setInt(1, 10);
            stmt.setInt(2, 6);
            stmt.setString(3, "city");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public void setPosition(String position) throws SQLException {
        Database db = new Database();
        String query = "UPDATE Character SET Position = ?";
        db.setData(query,position);
    }
    public String position() throws SQLException {
        Database db = new Database();
        String position = db.getData("Character", "Position").getString("Position");
        db.close();
        return switch (position) {
            case "home" -> "scenes/home-view.fxml";
            case "company" -> "scenes/company-view.fxml";
            case "bank" -> "scenes/bank-view.fxml";
            case "shop" -> "scenes/shop-view.fxml";
            case "municipality" -> "scenes/municipality-view.fxml";
            default -> "scenes/city-view.fxml";
        };
    }
}
