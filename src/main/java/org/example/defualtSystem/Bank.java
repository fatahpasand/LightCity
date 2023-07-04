package org.example.defualtSystem;

import org.example.Database;
import org.example.interfaces.BankInterface;
import org.example.models.User;

import java.sql.*;

public class Bank  implements BankInterface {
    @Override
    public void add(User user) throws SQLException {
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Bank (User, Balance) VALUES (?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setDouble(2, 10.0);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public double money() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Bank", "Balance");
        double balance = 0;
        while (rs.next()) {
            balance = rs.getDouble("Balance");
        }
        return balance;
    }
    public void modMoney(double amount) throws SQLException {
        Database db  = new Database();
        String query = "UPDATE Bank SET Balance = ?";
        db.setData(query, amount);
    }
}
