package chat.auth;

import java.sql.*;

public class BaseAuthService implements AuthService {

    public static Connection connection;
    public static Statement stmt;
    public static ResultSet rs;

    public static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:ChatServer\\src\\main\\resources\\db\\main.db");
        stmt = connection.createStatement();
    }

    public static void disconnection() throws SQLException {
        connection.close();
    }


    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException {
        connection();
        rs = stmt.executeQuery(String.format("SELECT password, username FROM users WHERE login = '%s'", login));
        String username = rs.getString("username");
        System.out.println(rs.getString("username"));

        if(rs.getString("password").equals(password)) {
            disconnection();
            return username;
            }
        disconnection();
        return null;
    }

    @Override
    public void close() {
        System.out.println("Сервис аутентификации завершен");

    }
}
