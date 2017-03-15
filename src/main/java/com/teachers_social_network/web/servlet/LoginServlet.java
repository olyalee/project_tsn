package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.impl.UserServiceImpl;
import com.teachers_social_network.service.interfaces.UserService;
import com.teachers_social_network.web.servlet.model.FieldValidation;
import com.teachers_social_network.web.servlet.model.FormValidation;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Olya Lee on 08.01.2017.
 */
@Singleton
//@WebServlet("/login")  //@WebServlet("/jsp")
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

    //
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);

        final Credentials credentials = Credentials.builder().login(req.getParameter(LOGIN)).password(req.getParameter(PASSWORD)).build();
        logger.info("doPost " + req.getParameter(LOGIN) + " " + req.getParameter(PASSWORD));

        final FormValidation validation = validate(credentials);

        if (validation.isValid()) {

            final Optional<User> user = userService.getByCredentials(credentials);

            if (!user.isPresent()) {
                validation.getErrors().put("INVALID_CREDENTIALS", true);
                req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
            } else {
                final HttpSession session = req.getSession(true);
                session.setAttribute("user", user.get());
                req.setAttribute("login", user.get().getLogin());
                req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("validtion", validation);
            req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
        }

//       if(!validation.isValid()){
//           req.setAttribute("validtion",validation);
//           req.getRequestDispatcher("WEB-INF/index.jsp").forward(req,resp);
//           return;
//       }

//        resp.sendRedirect(req.getContextPath());
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
