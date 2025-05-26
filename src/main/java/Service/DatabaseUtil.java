package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=ToyRoom;encrypt=true;trustServerCertificate=true";
    private static final String USER = "myuser"; // твій логін
    private static final String PASSWORD = "MyStrongPassword!"; // твій пароль


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
