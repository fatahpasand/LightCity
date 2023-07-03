package org.example.interfaces;

import org.example.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseInterface {
    boolean createNewDatabase() throws SQLException;
    void deleteDatabase() throws SQLException;
    void createTables() throws SQLException;
    public ResultSet getData(String tableName, String columnName) throws SQLException;
}
