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
 * Other user profile Servlet
 */
@Singleton
public class OtherProfileServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ProfileServlet.class);
    private String colleagueLogin;

    private final UserService userService;
    private final SecurityService securityService;

    @Inject
    public OtherProfileServlet(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        colleagueLogin = req.getParameter("colleagueLogin");
        logger.debug(colleagueLogin);

        User colleague = userService.getByLogin(colleagueLogin);
        logger.debug(colleague.getFirstName());

        List<Education> colleagueEducation = userService.getEducationByLogin(colleagueLogin);

        req.setAttribute("colleague", colleague);

        req.setAttribute("colleagueEducationList", colleagueEducation);

        req.getRequestDispatcher("/WEB-INF/jsp/otherProfile.jsp").forward(req,resp);
    }
}
