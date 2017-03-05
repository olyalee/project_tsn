package com.teachers_social_network.web.servlet;


import lombok.extern.java.Log;
import org.apache.log4j.Logger;


import javax.inject.Singleton;
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
@Log
public class RootServlet extends HttpServlet{
//    final static Logger logger = Logger.getLogger(RootServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet in RootServlet");
        req.getSession(false);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost in RootServlet");
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
