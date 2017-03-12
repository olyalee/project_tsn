package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Colleagues;
import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.ColleagueService;
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
 * Created by Olya Lee on 10.03.2017.
 */
@Singleton
public class ColleaguesServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ColleaguesServlet.class);

    private final UserService userService;
    private final SecurityService securityService;
    private final ColleagueService colleagueService;

    @Inject
    ColleaguesServlet(UserService userService, SecurityService securityService, ColleagueService colleagueService){
        this.userService = userService;
        this.securityService = securityService;
        this.colleagueService = colleagueService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        String login = user.getLogin();

        List<User> colleagues = colleagueService.listOfColleagues(login);
        session.setAttribute("colleaguesList",colleagues);

        req.getRequestDispatcher("/WEB-INF/jsp/colleagues.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        String login = user.getLogin();

        if(req.getParameter("addColleague")!=null){
            if(colleagueService.addColleague(login,req.getParameter("colleagueToAdd"))){
                req.setAttribute("wasAdded",true);
            }else{
                req.setAttribute("wasAdded", false);
            }
        }

        if(req.getParameter("removeColleague")!=null){
            if(colleagueService.removeColleague(login,req.getParameter("colleagueToRemove"))){
                req.setAttribute("wasRemoved",true);
            }else{
                req.setAttribute("wasRemoved", false);
            }
        }

        List<User> colleagues = colleagueService.listOfColleagues(login);
        session.setAttribute("colleaguesList",colleagues);

        req.getRequestDispatcher("/WEB-INF/jsp/colleagues.jsp").forward(req,resp);
    }


}
