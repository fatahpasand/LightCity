package org.example.defualtSystem;

import org.example.Database;
import org.example.interfaces.MunicipalityInterface;
import org.example.models.User;

import java.sql.*;
import java.util.Random;

public class Municipality implements MunicipalityInterface {
    @Override
    public void add(User user) throws SQLException {
        Random random = new Random();
        int[] prices = new int[20];
        for (int i = 0; i < 20; i++) {
            int randomNum = random.nextInt(3);
            if (randomNum == 0) {
                prices[i] = 20;
            } else if (randomNum == 1) {
                prices[i] = 40;
            } else {
                prices[i] = 60;
            }
        }

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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Municipality " +
                    "(Price) VALUES (?)");
            for (int i = 0; i < 20; i++) {
                stmt.setInt(1, prices[i]);
                stmt.executeUpdate();
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public boolean buyLand(String land_id) throws SQLException {
        Database db  = new Database();
        Bank bank = new Bank();
        ResultSet rs = db.getData("Municipality", "*");
        int price = 0;
        while (rs.next()) {
            if (Integer.parseInt(land_id) == rs.getInt("land_id") && !rs.getBoolean("Owned")) {
                price = rs.getInt("Price");
            }
        }
        if((bank.money() - price) >= 0) {
            bank.modMoney(bank.money() - price);
            String query = "UPDATE Municipality SET Owned = 1 WHERE Land_ID = ?";
            db.setData(query, land_id);
            return true;
        }else{
            return false;
        }
    }
    public void sellLand(String land_id) throws SQLException {
        Database db = new Database();
        Bank bank = new Bank();
        ResultSet rs = db.getData("Municipality", "*");
        int price = 0;
        while (rs.next()) {
            if (Integer.parseInt(land_id) == rs.getInt("land_id") && rs.getBoolean("Owned")) {
                price = rs.getInt("Price");
            }
        }
        bank.modMoney(bank.money() + price);
        String query = "UPDATE Municipality SET Owned = 0 WHERE Land_ID = ?";
        db.setData(query, land_id);
    }
}
