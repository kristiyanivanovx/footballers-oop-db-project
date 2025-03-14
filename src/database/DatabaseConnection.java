package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static Connection connection = null;

    public static Connection getConnection() {
        try {
//            Class.forName("org.h2.Driver");
//            connection=DriverManager.getConnection("jdbc:h2:tcp://localhost/C:\\uni\\footballersH2DB", "sa", "fbdb");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;database=fbDB;encrypt=true;trustServerCertificate=true;integratedSecurity=false;user=AdminUser;password=ABCD;";
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
