package com.teachers_social_network.web.servlet;

import javax.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olya Lee on 08.01.2017.
 */
@Singleton
//@WebServlet("/login")  //@WebServlet("/jsp")
public class LoginServlet extends HttpServlet{

//    private final UserService userService = new UserServiceImpl(new PgUserDao(new SecurityServiceImpl()),new SecurityServiceImpl());
//
//    public static final String LOGIN = "login";
//    public static final String PASSWORD = "password";
//
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }
//
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
//       final Credentials credentials = Credentials.builder().login(req.getParameter(LOGIN)).password(req.getParameter(PASSWORD)).build();
//
//       final FormValidation validation = validate(credentials);
//
//       if(validation.isValid()){
//           final Optional<User> user = userService.getByCredentials(credentials);
//
//           if(!user.isPresent()){
//               validation.getErrors().put("INVALID_CREDENTIALS", true);
//           }else{
//               final HttpSession session = req.getSession(true);
//               session.setAttribute("user",user.get());
//           }
//       }
//
//       if(!validation.isValid()){
//           req.setAttribute("validtion",validation);
//           req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
//           return;
//       }
//
//
//        resp.sendRedirect(req.getContextPath());
//    }
//
//    static FormValidation validate(Credentials credentials) {
//        final FormValidation validation = new FormValidation();
//
//        if(credentials.getLogin()==null || credentials.getLogin().isEmpty()){
//            validation.getFields().put(LOGIN, FieldValidation.builder().isEmptyField(true).build());
//        }
//
//        if(credentials.getPassword()==null || credentials.getPassword().isEmpty()){
//            validation.getFields().put(PASSWORD, FieldValidation.builder().isEmptyField(true).build());
//        }
//
//
//        return validation;
    }
}
