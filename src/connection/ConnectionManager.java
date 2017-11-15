package connection;

import java.sql.*;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/forecasts?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "кщще";

    public static Connection newConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
