package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.SecurityService;
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
import java.util.List;

/**
 * Created by Olya Lee on 08.03.2017.
 */

@Singleton
public class ProfileServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ProfileServlet.class);
    private String login;

    private final UserService userService;
    private final SecurityService securityService;

    @Inject
    public ProfileServlet(UserService userService, SecurityService securityService){
        this.userService = userService;
        this.securityService = securityService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        login = user.getLogin();



        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        login = user.getLogin();

        logger.info("Getting education list for " + login);
        final List<Education> educations = userService.getEducationByLogin(login);

        session.setAttribute("educationsList", educations);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req,resp);
    }
}
