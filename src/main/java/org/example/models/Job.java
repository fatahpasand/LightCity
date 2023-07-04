package org.example.models;

import org.example.Database;
import org.example.Game;
import org.example.defualtSystem.Bank;
import org.example.interfaces.JobInterface;

import java.sql.*;
import java.util.Random;

public class Job implements JobInterface {
    private Random random;
    public Job() {
        random = new Random();
    }

    public void add(User user) throws SQLException{
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Job " +
                    "(Work_count, Job_position) VALUES (?, ?)");
            stmt.setInt(1, 0);
            stmt.setString(2, "Junior");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public String generateExpression() {
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        int c = random.nextInt(10) + 1;

        // Generate a random mathematical expression using addition and subtraction
        String expr = a + (random.nextBoolean() ? " + " : " - ") + b;
        expr += (random.nextBoolean() ? " + " : " - ") + c;

        return expr;
    }
    public boolean checkAnswer(String expression, int answer) {
        // Evaluate the expression and check if the answer is correct
        return evaluateExpression(expression) == answer;
    }
    private int evaluateExpression(String expression) {
        // Split the expression into tokens and evaluate the expression using addition and subtraction only
        String[] tokens = expression.split(" ");
        int result = Integer.parseInt(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            char op = tokens[i].charAt(0);
            int operand = Integer.parseInt(tokens[i + 1]);
            result = (op == '+') ? result + operand : result - operand;
        }
        return result;
    }
    public void worked() throws SQLException {
        Database db  = new Database();
        Bank bank = new Bank();
        String query = "UPDATE Job SET Work_count = ?";
        db.setData(query, workCount()+1);
        bank.modMoney(bank.money() + salary());
        Game game = new Game();
        game.timePassed();
        Food food = new Food();
        query = "UPDATE Character SET Food = ?";
        db.setData(query,food.food()-2);
    }
    public void promotion(int n) throws SQLException {
        String position;
        if (n == 1){
            position = "junior";
        }else if(n == 2){
            position = "senior";
        }else{
            position = "lead";
        }
        Database db  = new Database();
        String query = "UPDATE Job SET Job_position = ?";
        db.setData(query, position);
    }
    public String jobPosition() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Job", "Job_position");
        String Job_position = null;
        while (rs.next()) {
            Job_position = rs.getString("Job_position");
        }
        return Job_position;
    }
    public int workCount() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Job", "Work_count");
        int Work_count = 0;
        while (rs.next()) {
            Work_count = rs.getInt("Work_count");
        }
        return Work_count;
    }
    public int salary() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Job", "Job_position");
        String Job_position = null;
        while (rs.next()) {
            Job_position = rs.getString("Job_position");
        }
        return switch (Job_position){
            case "junior" -> 20;
            case "senior" -> 40;
            case "lead" -> 60;
            default -> 20;
        };
    }
}