package pl.knpj.servlet.filters;

import pl.knpj.servlet.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Domino on 01.12.2016.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        String url = httpRequest.getRequestURL().toString();

        if (url.endsWith("login.do") || url.endsWith("index.html") || url.endsWith("/")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = httpRequest.getSession();

            if (session == null) {
                sendNoAccess(httpResponse);
            } else {
                User user = (User) session.getAttribute("user");

                if (user == null) {
                    sendNoAccess(httpResponse);
                } else {
                    chain.doFilter(req, resp);
                }
            }
        }
    }

    private void sendNoAccess(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpResponse.getWriter().println("No access!!");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
