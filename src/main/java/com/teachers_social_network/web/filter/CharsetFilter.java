package com.teachers_social_network.web.filter;

import com.google.inject.Singleton;

import javax.servlet.*;
import java.io.IOException;

/**
 * WebFilter which helps to set request's encoding to UTF8
 */
@Singleton
public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
