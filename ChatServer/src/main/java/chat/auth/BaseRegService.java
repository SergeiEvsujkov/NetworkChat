package chat.auth;

import java.sql.*;

public class BaseRegService {


    public static String regInDatabase(String login, String username, String password) throws SQLException, ClassNotFoundException {
        BaseAuthService.connection();
        int result = BaseAuthService.stmt.executeUpdate(String.format("INSERT INTO users (login, username, password)  VALUES ('%s', '%s', '%s')", login, username, password));
        BaseAuthService.disconnection();
        if (result == 1) {
            return username;
        } else {
            return null;
        }

    }


}