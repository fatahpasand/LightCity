package org.example.defualtSystem;

import org.example.Database;
import org.example.Game;
import org.example.models.Food;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Life {
    public boolean life() throws SQLException {
        Database db  = new Database();
        Food food = new Food();
        if (sleepStat() < 0){
            String query = "UPDATE Character SET Food = ?";
            db.setData(query,food.food()-2);
        }
        return food.food() >= 0;
    }
    public int sleepStat() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Character", "Sleep");
        int balance = 0;
        while (rs.next()) {
            balance = rs.getInt("Sleep");
        }
        return balance;
    }
    public void sleep() throws SQLException{
        Game game = new Game();
        game.timePassed();
        game.timePassed();
        Database db  = new Database();
        String query = "UPDATE Character SET Sleep = ?";
        db.setData(query,sleepStat()+4);
    }
}
