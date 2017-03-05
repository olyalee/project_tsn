package com.teachers_social_network.web.servlet;

import com.google.inject.Singleton;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olya Lee on 21.01.2017.
 */
@Singleton
//@WebServlet("")
public class RootServlet extends HttpServlet{
    final static Logger logger = Logger.getLogger(RootServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet in RootServlet");
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}
