package com.teachers_social_network.web.servlet;


import org.apache.log4j.Logger;


import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Root Servlet
 */
@Singleton
public class RootServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(RootServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet in RootServlet");
        req.getSession(false);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost in RootServlet");
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
