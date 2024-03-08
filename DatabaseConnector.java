package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection connect() throws SQLException {
        final String JDBC_URL = "jdbc:mysql://localhost:3306/mess_db";
        final String USERNAME = "root";
        final String PASSWORD = "1234";
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
