package com.teachers_social_network.web.servlet;

import com.google.inject.Inject;
import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.UserService;
import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Registration Servlet
 */
@Singleton
public class RegistrationServlet extends HttpServlet {
    private final UserService userService;

    final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    public static final String LOGIN = "newLogin";
    public static final String PASSWORD = "newPassword";

    @Inject
    public RegistrationServlet(UserService userService) {
        this.userService = userService;
        logger.info("userService was injected");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet from " + request.getParameter(LOGIN));
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost from " + req.getParameter(LOGIN));

        final Credentials credentials = Credentials.builder().login(req.getParameter(LOGIN)).password(req.getParameter(PASSWORD)).build();
        final User newUser = User.builder()
                .login(req.getParameter(LOGIN))
                .passwordHash(req.getParameter(PASSWORD))
                .firstName(req.getParameter("newFirstname"))
                .lastName(req.getParameter("newLastname"))
                .gender(userService.parseGender(req.getParameter("newGender")))
                .birthDate(userService.parseDate(req.getParameter("newBirthDate")))
                .email(req.getParameter("newEmail"))
                .country(req.getParameter("newCountry"))
                .city(req.getParameter("newCity"))
                .science_field(req.getParameter("newScienceField"))
                .working_place(req.getParameter("newPlace"))
                .position(req.getParameter("newPosition")).build();

            final Optional<User> user = userService.getByCredentials(credentials);

            if (!user.isPresent()) {
                //add new user
                Optional<User> addedUser = userService.addUser(newUser);
                if (addedUser.isPresent()) {
                    logger.info("User was added");
                    final HttpSession session = req.getSession(true);
                    session.setAttribute("user", newUser);
                } else {
                    //couldn't add new user
                    logger.info("couldn't add new user");
                }
            } else {
                //user with such login is already exist
                logger.info("couldn't add new user - user with such login is already exist");
            }

        resp.sendRedirect(req.getContextPath());
    }
}
