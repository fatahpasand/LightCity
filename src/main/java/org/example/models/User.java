package org.example.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void add(){
        Connection conn = null;
        String dbPath = "src/main/resources/org/example/database/" + username + ".db";

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
            String hashedPassword = hashPassword(password);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hash) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
