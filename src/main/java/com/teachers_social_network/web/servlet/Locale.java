package com.teachers_social_network.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Olya Lee on 03.02.2017.
 */

@WebServlet("/locale")
public class Locale extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        session.setAttribute("locale", req.getParameter("locale"));

        final String redirectTo = req.getParameter("redirect_to");
        if(redirectTo != null && ! redirectTo.isEmpty()){
            resp.sendRedirect(redirectTo);
        }else{
            resp.sendRedirect(req.getContextPath());
        }
    }
}
