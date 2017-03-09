package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.ProfileService;
import com.teachers_social_network.service.interfaces.SecurityService;
import com.teachers_social_network.service.interfaces.UserService;
import com.teachers_social_network.web.servlet.model.Profile;

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
    private final UserService userService;
    private final SecurityService securityService;
    private final ProfileService profileService;

    @Inject
    public ProfileServlet(UserService userService, SecurityService securityService, ProfileService profileService){
        this.userService = userService;
        this.securityService = securityService;
        this.profileService = profileService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        final User userFromDao = userService.getByLogin(user.getLogin());
        final List<Education> educations = profileService.getEducationByLogin(user.getLogin());

        final Profile profile = Profile.builder()
                .user(userFromDao)
                .educations(educations)
                .build();

        req.setAttribute("profile", profile);
        session.setAttribute("educationsList", educations);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req,resp);
    }
}
