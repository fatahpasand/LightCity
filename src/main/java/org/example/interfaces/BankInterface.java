package org.example.interfaces;

import org.example.models.User;

import java.sql.SQLException;

public interface BankInterface {
    void add(User user) throws SQLException;
}
