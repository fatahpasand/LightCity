package org.example.interfaces;

import org.example.models.User;

import java.sql.SQLException;

public interface GameInterface {
    void continueGame(User user);
    void startGame(User user) throws SQLException;
    void add(User user) throws SQLException;
}
