package pl.knpj.servlet.servlet;

import pl.knpj.servlet.dao.UserDAO;
import pl.knpj.servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Login servlet for handling login requests.
 */

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login");
        String password = req.getParameter("password");
        UserDAO dao = new UserDAO();
        try {
            if (!dao.isUsernameMatchingPassword(username, password)) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("Unauthorized!");
            } else {
                User user = dao.getUserByUsername(username);

                HttpSession session = req.getSession(true);

                if (session.isNew()) {
                    session.setAttribute("user", user);
                }

//                req.setAttribute("username", "Ala ma kota :)");

                List quizList = Arrays.asList("quiz1", "quiz2", "quiz3", "quiz4");

                req.setAttribute("quizList", quizList);

                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace(); //TODO

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Error has occurred: " + e.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.html");
    }
}
