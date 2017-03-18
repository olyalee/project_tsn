package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.Gender;
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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ProfileServlet(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        login = user.getLogin();

        if (req.getParameter("editInfo") != null && userService.getByLogin(login)!=user) {
            logger.debug("build updated user");
            User updatedUser = User.builder()
                    .login(user.getLogin())
                    .passwordHash(user.getPasswordHash())
                    .firstName(req.getParameter("firstName"))
                    .lastName(req.getParameter("lastName"))
                    .gender(userService.parseGender(req.getParameter("gender")))
                    .email(req.getParameter("email"))
                    .birthDate(Date.valueOf(userService.parseDate(req.getParameter("birthDate")).toLocalDate()))                               //(userService.parseDate("01.02.1978"))                      //
                    .country(req.getParameter("country"))
                    .city(req.getParameter("city"))
                    .science_field(req.getParameter("scienceField"))
                    .working_place(req.getParameter("workingPlace"))
                    .position(req.getParameter("position"))
                    .build();

            if (userService.updateUser(updatedUser)) {
                logger.debug("setting updated user for session if update was successful");
                session.setAttribute("user", updatedUser);
                req.setAttribute("wasUpdated", true);
            } else {
                logger.debug("couldn't update user's info");
                req.setAttribute("wasUpdated", false);
            }
        }

        if(req.getParameter("editEducation")!=null){
            logger.debug("build updated education");
            String id = req.getParameter("educationId");
            Education updatedEducation = Education.builder()
                    .id(Integer.parseInt(id))
                    .login(login)
                    .educationType(req.getParameter("educationType"+id))
                    .placeType(req.getParameter("placeType"+id))
                    .placeTitle(req.getParameter("placeTitle"+id))
                    .major(req.getParameter("major"+id))
                    .startYear(Integer.parseInt(req.getParameter("startYear"+id)))
                    .endYear(Integer.parseInt(req.getParameter("endYear"+id)))
                    .build();

            logger.debug(id);
            logger.debug(login);
            logger.debug(req.getParameter("educationType"+id));
            logger.debug(req.getParameter("placeType"+id));
            logger.debug(req.getParameter("placeTitle"+id));
            logger.debug(req.getParameter("major"+id));
            logger.debug(Integer.parseInt(req.getParameter("startYear"+id)));
            logger.debug(Integer.parseInt(req.getParameter("endYear"+id)));

            if(userService.updateEducation(updatedEducation)){
                logger.debug("education was updated");
                req.setAttribute("educationWasUpdated", true);
            }else{
                logger.debug("couldn't update education");
                req.setAttribute("educationWasUpdated", false);
            }
        }

        if(req.getParameter("addFormForNewEducation")!=null){
            req.setAttribute("addNew",true);
        }

        if(req.getParameter("addNewEducation")!=null){
            Education newEducation = Education.builder()
                    .id(1)
                    .login(login)
                    .educationType(req.getParameter("newEducationType"))
                    .placeType(req.getParameter("newPlaceType"))
                    .placeTitle(req.getParameter("newPlaceTitle"))
                    .major(req.getParameter("newMajor"))
                    .startYear(Integer.parseInt(req.getParameter("newStartYear")))
                    .endYear(Integer.parseInt(req.getParameter("newEndYear")))
                    .build();

            if(userService.addEducation(newEducation)){
                logger.debug("new education was added");
                req.setAttribute("educationWasAdded", true);
            }else{
                logger.debug("couldn't add education");
                req.setAttribute("educationWasAdded", false);
            }
        }

        if(req.getParameter("deleteEducation")!=null){
            int id = Integer.parseInt(req.getParameter("educationId"));
            if(userService.deleteEducation(id)){
                logger.debug("education was deleted");
                req.setAttribute("educationWasDeleted", true);
            }else{
                logger.debug("education was deleted");
                req.setAttribute("educationWasDeleted", false);
            }
        }

        final List<Education> educationsUpdate = userService.getEducationByLogin(login);
        session.setAttribute("educationsList", educationsUpdate);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        login = user.getLogin();

        logger.info("Getting education list for " + login);
        final List<Education> educations = userService.getEducationByLogin(login);

        session.setAttribute("educationsList", educations);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

}
