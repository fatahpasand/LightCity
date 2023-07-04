package org.example;

import javafx.application.Platform;
import org.example.interfaces.DatabaseInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Database implements DatabaseInterface {
    private Connection conn;
    private static String dbname;

    public static void setdbname(String dbname){
        Database.dbname = dbname;
    }
    public boolean createNewDatabase() throws SQLException {
        File file = new File("src/main/resources/org/example/database/" + dbname + ".db");
        boolean fileExists = file.exists();
        if (!fileExists) {
            conn = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
            conn.close();
            return true;
        }
        return false;
    }
    public void deleteDatabase(){
        //I tried everything I could think of to delete the database, but it didn't work. Kill me :)

        Platform.runLater(() -> {
            Path path = Paths.get("src/main/resources/org/example/database/" + dbname + ".db");
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + path);

                connection.close();

                Thread.sleep(100);

                Files.delete(path);
                System.out.println("File deleted successfully.");
            } catch (IOException | InterruptedException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println("Exception occurred while closing connection: " + e.getMessage());
                    }
                }
            }
        });
    }
    public void createTables() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/org/example/database/" +
                dbname + ".db");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS User (" +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL" +
                ")");
        stmt.execute("CREATE TABLE IF NOT EXISTS Character (" +
                "Food Integer," +
                "Sleep Integer," +
                "Position TEXT," +
                "CHECK ( (Food <= 10) )" +
                ")");
        stmt.execute("CREATE TABLE IF NOT EXISTS Job (" +
                "Work_count Integer," +
                "Job_position TEXT" +
                ")");
        stmt.execute("CREATE TABLE IF NOT EXISTS Municipality (" +
                "Land_id INTEGER PRIMARY KEY," +
                "Price Integer NOT NULL," +
                "Owned Boolean DEFAULT False" +
                ")");
        stmt.execute("CREATE TABLE IF NOT EXISTS Game (" +
                "Time INTEGER," +
                "Day_count INTEGER," +
                "CHECK ( (Time <= 6) )" +
                ")");
        stmt.execute("CREATE TABLE IF NOT EXISTS Bank (" +
                "User Text," +
                "Balance Real," +
                "FOREIGN KEY ( User ) REFERENCES User (username)" +
                ")");
        stmt.close();
    }
    public ResultSet getData(String tableName, String columnName) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/org/example/database/" +
                dbname + ".db");
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT " + columnName + " FROM " + tableName);
    }
    public void setData(String query, Object... params) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/org/example/database/" +
                dbname + ".db");
        PreparedStatement ps = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
