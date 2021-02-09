package chat.auth;

import java.sql.*;

public class BaseRegService {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    private  static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\evsuj\\IdeaProjects\\NetworkChat\\ChatServer\\src\\main\\resources\\db\\main.db");
        stmt = connection.createStatement();
    }

    private  static void disconnection() throws SQLException {
        connection.close();
    }


    public void start() {
        System.out.println("Сервис регистрации запущен");
    }


    public static String regInDatabase(String login, String username, String password) throws SQLException, ClassNotFoundException {
        connection();
        int result = stmt.executeUpdate(String.format("INSERT INTO users (login, username, password)  VALUES ('%s', '%s', '%s')", login, username, password));
        disconnection();
        if (result == 1) {
            return username;
        } else {
            return null;
        }

    }


    public void close() {
        System.out.println("Сервис регистрации завершен");

    }
}