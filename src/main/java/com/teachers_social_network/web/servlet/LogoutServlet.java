package com.teachers_social_network.web.servlet;

import org.apache.log4j.Logger;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olya Lee on 08.03.2017.
 */
@Singleton
public class LogoutServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("user is logging out");
        req.getSession().removeAttribute("user");
        req.getSession().invalidate();
        req.getRequestDispatcher("/WEB-INF/jsp/logout.jsp").forward(req,resp);
    }
}
