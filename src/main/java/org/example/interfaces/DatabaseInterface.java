package org.example.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseInterface {
    boolean createNewDatabase() throws SQLException;
    void deleteDatabase() throws SQLException;
    void createTables() throws SQLException;
    ResultSet getData(String tableName, String columnName) throws SQLException;
    void close() throws SQLException;
}
