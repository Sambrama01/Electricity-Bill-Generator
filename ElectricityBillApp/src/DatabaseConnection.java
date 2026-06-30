import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3307/electricitydb";
            String user = "root";
            String password = "Sambrama@2006";

            Connection con = DriverManager.getConnection(url, user, password);
            return con;

        } catch (Exception e) {
            System.out.println("DB Connection Failed: " + e);
            return null;
        }
    }
}