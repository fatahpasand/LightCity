package org.example.models;

import org.example.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Food {
    public int food() throws SQLException {
        Database db  = new Database();
        ResultSet rs = db.getData("Character", "Food");
        int food = 0;
        while (rs.next()) {
            food = rs.getInt("Food");
        }
        return food;
    }
    public void eat(int food) throws SQLException{
        Database db  = new Database();
        String query = "UPDATE Character SET Food = ?";
        db.setData(query,food()+food);
    }
}
