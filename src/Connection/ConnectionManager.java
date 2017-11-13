package Connection;

import java.sql.*;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/forecasts";
    private static final String USER = "root";
    private static final String PASSWORD = "кщще";

//    private static Connection con;
//    private static Statement stmt;
//    private static ResultSet rs;

    public static Connection newConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
