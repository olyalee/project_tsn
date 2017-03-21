package com.teachers_social_network.web.servlet;

import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Locale Servlet
 */

@Singleton
public class LocaleServlet extends HttpServlet{
    final static Logger logger = Logger.getLogger(LocaleServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost " + req.getParameter("locale"));

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
