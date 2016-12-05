package pl.knpj.servlet.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.knpj.servlet.model.User;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by rpi on 24.11.16.
 */
public class UserDAOTest {

    private UserDAO userDAO;

    @Before
    public void setup() {
        userDAO = new UserDAO();
    }

    @Ignore("Should be IT test - now it is dependent on db data")
    @Test
    public void selectExistingUserTest() throws SQLException {
//        String username = "dominik";
//        User result = userDAO.getUserByUsername(username);
//        assertNotNull(result);
//        assertEquals(username, result.getUsername());
    }

}
