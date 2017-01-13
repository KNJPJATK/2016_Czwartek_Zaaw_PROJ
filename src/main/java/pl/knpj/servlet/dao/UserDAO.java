package pl.knpj.servlet.dao;

import pl.knpj.servlet.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO class for retrieving data from vuser table
 */
public class UserDAO extends BaseDAO {

    /**
     * Gets user from db by username.
     *
     * @param username
     * @return user from database, or null if not found
     */
    public User getUserByUsername(String username) {
        List<User> users = executeNamedQuery("GET_USER_BY_USERNAME", User.class, username);
        if (users.size() != 1) {
            return null;
        }
        return users.get(0);
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

}
