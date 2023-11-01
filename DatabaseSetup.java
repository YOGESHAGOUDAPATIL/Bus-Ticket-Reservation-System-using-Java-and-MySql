import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Yogesh@1999";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        Statement statement = null;

        try {
            // Create a connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Create a database
            String createDBQuery = "CREATE DATABASE IF NOT EXISTS ticket_bus_reservation";
            statement.executeUpdate(createDBQuery);

            // Use the database
            String useDBQuery = "USE ticket_bus_reservation";
            statement.executeUpdate(useDBQuery);

            // Create a table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS passengers_list (" +
                    "id INT AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "age INT, " +
                    "gender VARCHAR(8), " +
                    "pickup VARCHAR(25), " +
                    "dropout VARCHAR(10), " +
                    "PRIMARY KEY (id)" +
                    ")";
            statement.executeUpdate(createTableQuery);

            System.out.println("Database and table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
