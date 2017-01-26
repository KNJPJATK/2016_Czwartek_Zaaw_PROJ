package pl.knpj.servlet.dao;

import org.apache.commons.dbutils.DbUtils;
import pl.knpj.servlet.config.Config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base DAO class to data from database
 */
public abstract class BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class.getName());
    private final Config config = Config.getInstance();

    private Connection makeConnection() throws SQLException, ClassNotFoundException {
        Class.forName(config.getProperty("db.driver-class"));

        Connection res =
                DriverManager.getConnection(
                        config.getProperty("db.url"),
                        config.getProperty("db.user"),
                        config.getProperty("db.password")
                );
        return res;
    }

    /**
     * Method used to execute queries on database and parsing data to specific dao class. Parsing is done in subclass.
     *
     * @param sql db query to execute by prepared statement
     * @param params parameters for query
     * @return retrieved and parsed object from db
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    protected ResultSet executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = makeConnection();
            stmt = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            LOGGER.log(Level.INFO, stmt.toString());

            rs = stmt.executeQuery();

            return rs;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(con);
        }
    }

    /**
     * Method used to execute update on database.
     *
     * @param sql db query to execute by prepared statement
     * @param params parameters for query
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    protected void executeUpdate (String sql, Object... params) throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = makeConnection();
            stmt = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }

            LOGGER.log(Level.INFO, stmt.toString());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        } finally {
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(con);
        }
    }

    /**
     * Method to get Object from db by query SQL
     *
     * @param sql db query to execute by prepared statement
     * @param params parameters for query
     * @return Object from db
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    protected Object getObjectFromQuery (String sql, Object... params) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(sql, params);
        return parseResultSet(rs);
    }

    /**
     * Method to parse object from {@link ResultSet}
     *
     * @param rs result set to parse
     * @return parsed object
     * @throws SQLException
     * @throws ClassNotFoundException no database driver found
     */
    protected abstract Object parseResultSet(ResultSet rs) throws SQLException, ClassNotFoundException;
}
