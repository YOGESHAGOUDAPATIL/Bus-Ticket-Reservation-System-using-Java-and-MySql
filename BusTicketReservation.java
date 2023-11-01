import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BusTicketReservation {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ticket_bus_reservation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Yogesh@1999";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Passenger Details");
            System.out.println("2. Show All Passengers");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPassengerDetails(scanner);
                    break;
                case 2:
                    showAllPassengers();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPassengerDetails(Scanner scanner) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.print("Enter passenger name: ");
            String name = scanner.next();
            System.out.print("Enter passenger age: ");
            int age = scanner.nextInt();
            System.out.print("Enter passenger gender: ");
            String gender = scanner.next();
            System.out.print("Enter passenger Pick Up Point: ");
            String pickup = scanner.next();
            System.out.print("Enter passenger Destination: ");
            String dropout = scanner.next();

            String query = "INSERT INTO passengers_list (name, age, gender, pickup, dropout) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, pickup);
            preparedStatement.setString(5, dropout);
            preparedStatement.executeUpdate();

            System.out.println("Passenger details added successfully.");

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showAllPassengers() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT * FROM passengers_list";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Passenger Details:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Age: " + resultSet.getInt("age") +
                        ", Gender: " + resultSet.getString("gender")+
                        ", Pick Up Point: " + resultSet.getString("pickup")+
                        ", Destination: " + resultSet.getString("dropout"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
