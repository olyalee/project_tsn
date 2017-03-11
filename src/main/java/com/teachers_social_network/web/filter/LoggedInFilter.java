package com.teachers_social_network.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Olya Lee on 08.01.2017.
 */
@WebFilter
public class LoggedInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final HttpSession session = request.getSession(false);

            final boolean isLoggedIn = session != null && session.getAttribute("user") != null;

            final String loginPage = request.getContextPath()+"/";
//            final String loginPage = "/TSN";

            if(!isLoggedIn){

               response.sendRedirect(loginPage);
               return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
