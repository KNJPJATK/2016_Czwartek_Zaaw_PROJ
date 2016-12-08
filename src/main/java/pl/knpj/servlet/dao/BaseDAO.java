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
    protected Object executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {
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

            return parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();

            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw e;
        } catch (ClassNotFoundException e) {

            throw e;
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(con);
        }
    }

    /**
     * Method to parse object from {@link ResultSet}
     *
     * @param rs result set to parse
     * @return parsed object
     * @throws SQLException
     */
    protected abstract Object parseResultSet(ResultSet rs) throws SQLException;

}
