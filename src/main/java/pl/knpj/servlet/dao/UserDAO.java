package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO class for retrieving data from vuser table
 */
public class UserDAO extends BaseDAO {

    private static final String ID_COLUMN = "id";
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";

    private static final String GET_USER_USERNAME_SQL =
            "SELECT id as 'id', username as 'username', password as 'password' " +
                    "FROM vuser where username = ?";

    /**
     * Gets user from db by username.
     *
     * @param username
     * @return user from database, or null if not found
     */
    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {

        return (User) executeQuery(GET_USER_USERNAME_SQL, username);
    }


    /**
     * This method retrieves user from db and check if password matches with this stored in db.
     *
     * @param username username to check
     * @param password password of usr to check
     * @return true if username and password matches, false otherwise
     */
    public boolean isUsernameMatchingPassword(String username, String password) throws SQLException, ClassNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        }

        return (user.getPassword().equals(password));
    }

    /**
     * {@inheritDoc}
     */
    protected Object parseResultSet(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        long id = rs.getLong(ID_COLUMN);
        String username = rs.getString(USERNAME_COLUMN);
        String password = rs.getString(PASSWORD_COLUMN);

        User user = new User(id, username, password);

        return user;
    }
}
