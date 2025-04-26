package sistemkasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/db_kasir";
            String user = "root";
            String pass = ""; 
            conn = DriverManager.getConnection(url, user, pass);
        }
        return conn;
    }
}

