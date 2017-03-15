package com.teachers_social_network.web.servlet;

import com.google.inject.Inject;

import javax.inject.Singleton;

import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.UserService;
import com.teachers_social_network.web.servlet.model.FieldValidation;
import com.teachers_social_network.web.servlet.model.FormValidation;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 *
 */
@Singleton
//@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService;

    final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    public static final String LOGIN = "newLogin";    // login
    public static final String PASSWORD = "newPassword";  //password

    @Inject
    public RegistrationServlet(UserService userService) {
        this.userService = userService;
        logger.info("userService was injected");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet from " + request.getParameter(LOGIN));
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
                .gender(Gender.valueOf((req.getParameter("newGender")).toUpperCase()))
                .birthDate(userService.parseDate(req.getParameter("newBirthDate")))
                .email(req.getParameter("newEmail"))
                .country(req.getParameter("newCountry"))
                .city(req.getParameter("newCity"))
                .science_field(req.getParameter("newScienceField"))
                .working_place(req.getParameter("newPlace"))
                .position(req.getParameter("newPosition")).build();

        final FormValidation validation = validate(credentials);

        if (validation.isValid()) {
            final Optional<User> user = userService.getByCredentials(credentials);

            if (!user.isPresent()) {
                //add new user
                Optional<User> addedUser = userService.addUser(newUser);
                if (addedUser.isPresent()) {
                    logger.info("User was added");
                    final HttpSession session = req.getSession(true);
                    session.setAttribute("user", newUser); //session.setAttribute("user",user.get());
                } else {
                    //couldn't add new user
                    logger.info("couldn't add new user");
                }
            } else {
                //user with such login is already exist
                logger.info("couldn't add new user - user with such login is already exist");
                validation.getErrors().put("INVALID_CREDENTIALS", true);
            }
        }

        if (!validation.isValid()) {
            req.setAttribute("validtion", validation);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath());
    }

    static FormValidation validate(Credentials credentials) {
        final FormValidation validation = new FormValidation();

        if (credentials.getLogin() == null || credentials.getLogin().isEmpty()) {
            validation.getFields().put(LOGIN, FieldValidation.builder().isEmptyField(true).build());
        }

        if (credentials.getPassword() == null || credentials.getPassword().isEmpty()) {
            validation.getFields().put(PASSWORD, FieldValidation.builder().isEmptyField(true).build());
        }


        return validation;
    }
}
