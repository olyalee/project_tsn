package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.UserService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Login Servlet
 */
@Singleton
public class LoginServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    private final UserService userService;

    public static final String LOGIN = "inputLogin";
    public static final String PASSWORD = "inputPassword";

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet" + request.getParameter("inputLogin"));
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Credentials credentials = Credentials.builder().login(req.getParameter(LOGIN)).password(req.getParameter(PASSWORD)).build();
        logger.info("doPost " + req.getParameter(LOGIN) + " " + req.getParameter(PASSWORD));

            final Optional<User> user = userService.getByCredentials(credentials);

            if (!user.isPresent()) {
                logger.debug("there is now such user" + req.getParameter(LOGIN) + " " + req.getParameter(PASSWORD));
                req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
            } else {
                logger.debug("user " + LOGIN + " was logged in");
                final HttpSession session = req.getSession(true);
                session.setAttribute("user", user.get());
                req.setAttribute("login", user.get().getLogin());
                req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }

}
