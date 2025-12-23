package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author $(bilal belhaj)
 **/
public class DbConnection {
    private static final String url = "jdbc:mysql://localhost:3306/pharmacyDB";
    private static final String user = "root";
    private static final String pass = "bilal2025";
    public static Connection conn;
    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
