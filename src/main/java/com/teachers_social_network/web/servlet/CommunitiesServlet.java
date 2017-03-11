package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.CommunityService;
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

@Singleton
public class CommunitiesServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(CommunitiesServlet.class);

    private final UserService userService;
    private final CommunityService communityService;

    @Inject
    public CommunitiesServlet(UserService userService, CommunityService communityService) {
        this.userService = userService;
        this.communityService = communityService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        String login = user.getLogin();

        List<Community> communities = communityService.communitiesForUser(login);
        session.setAttribute("communitiesList",communities);

        req.getRequestDispatcher("/WEB-INF/jsp/communities.jsp").forward(req,resp);
    }
}
