package pl.knpj.servlet.servlet;

import pl.knpj.servlet.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by rpi on 24.11.16.
 */

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login");
        String password = req.getParameter("password");
        UserDAO dao = new UserDAO();
        try {
            if (!dao.checkUserCredentials(username, password)) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("Unauthorized!");
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Authorized!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
