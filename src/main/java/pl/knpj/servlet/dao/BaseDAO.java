package pl.knpj.servlet.dao;

import org.postgresql.Driver;
import pl.knpj.servlet.config.Config;

import java.sql.*;

/**
 * Created by rpi on 24.11.16.
 */
public abstract class BaseDAO {

    private final Config config = Config.getInstance();

    private Connection makeConnection() throws SQLException {
        try {
            Class.forName(config.getProperty("db.driver-class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection res =
                DriverManager.getConnection(
                        config.getProperty("db.url"),
                        config.getProperty("db.user"),
                        config.getProperty("db.password")
                );
        return res;
    }

    protected ResultSet executeStatement(String sql, Object... params) throws SQLException {
        Connection con = null;
        try {
            con = makeConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }
            return stmt.executeQuery();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    protected void executeUpdate (String sql, Object... params) throws SQLException {
        Connection connection = null;
        try {
            connection = makeConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }
            stmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
