package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.UserService;
import com.teachers_social_network.web.servlet.model.FieldValidation;
import com.teachers_social_network.web.servlet.model.FormValidation;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

/**
 * Created by Olya Lee on 22.02.2017.
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
    private final UserService userService;

    public static final String LOGIN = "newLogin";    // login
    public static final String PASSWORD = "newPassword";  //password

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       final Credentials credentials = Credentials.builder().login(req.getParameter(LOGIN)).password(req.getParameter(PASSWORD)).build();
       final User newUser = User.builder()
               .login(req.getParameter(LOGIN))
               .passwordHash(req.getParameter(PASSWORD))
               .firstName(req.getParameter("newFirstname"))
               .lastName(req.getParameter("newLastname"))
               .gender(Gender.valueOf((req.getParameter("newGender")).toUpperCase()))
               .birthDate(Date.valueOf(req.getParameter("newBirthDate")))
               .email(req.getParameter("newEmail"))
               .country(req.getParameter("newCountry"))
               .city(req.getParameter("newCity"))
               .science_field(req.getParameter("newScienceField"))
               .working_place(req.getParameter("newPlace"))
               .position(req.getParameter("newPosition")).build();

       final FormValidation validation = validate(credentials);

       if(validation.isValid()){
           final Optional<User> user = userService.getByCredentials(credentials);

           if(!user.isPresent()){
               //add new user
               Optional<User> addedUser = userService.addUser(newUser);
               if(addedUser.isPresent()){
                   final HttpSession session = req.getSession(true);
                   session.setAttribute("user",user.get());
               }
               else{
                   //couldn't add new user
               }
           }else{
               //user with such login is already exist
               validation.getErrors().put("INVALID_CREDENTIALS", true);
           }
       }

       if(!validation.isValid()){
           req.setAttribute("validtion",validation);
           req.getRequestDispatcher("WEB-INF/index.jsp").forward(req,resp);
           return;
       }

        resp.sendRedirect(req.getContextPath());
    }

    static FormValidation validate(Credentials credentials) {
        final FormValidation validation = new FormValidation();

        if(credentials.getLogin()==null || credentials.getLogin().isEmpty()){
            validation.getFields().put(LOGIN, FieldValidation.builder().isEmptyField(true).build());
        }

        if(credentials.getPassword()==null || credentials.getPassword().isEmpty()){
            validation.getFields().put(PASSWORD, FieldValidation.builder().isEmptyField(true).build());
        }


        return validation;
    }
}
