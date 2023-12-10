
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
    }
}