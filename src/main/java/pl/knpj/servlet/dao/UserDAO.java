package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rpi on 24.11.16.
 */
public class UserDAO extends BaseDAO {

    private static final String GET_USER_USERNAME_SQL =
            "SELECT id, username, \"password\" " +
                    "FROM \"user\" where username = ?";

    public User getUserByUsername(String username) throws SQLException {
        ResultSet rs = executeStatement(GET_USER_USERNAME_SQL, "dominik");

        try {
            return getUserFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
        return null;
    }

    public boolean checkUserCredentials(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return (user.getPassword().equals(password));
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        rs.next();

        long id = rs.getLong(1);
        String username = rs.getString(2);
        String password = rs.getString(3);

        return new User(id, username, password);
    }
}
