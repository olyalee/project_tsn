package com.teachers_social_network.web.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.teachers_social_network.dao.DummyUserDao;
import com.teachers_social_network.dao.interfaces.*;
import com.teachers_social_network.dao.pg.*;
import com.teachers_social_network.service.impl.ProfileServiceImpl;
import com.teachers_social_network.service.impl.SecurityServiceImpl;
import com.teachers_social_network.service.impl.UserServiceImpl;
import com.teachers_social_network.service.interfaces.ProfileService;
import com.teachers_social_network.service.interfaces.SecurityService;
import com.teachers_social_network.service.interfaces.UserService;
import com.teachers_social_network.web.servlet.*;
import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import com.google.inject.Singleton;

import javax.servlet.annotation.WebListener;

/**
 *
 */
@WebListener
public class GuiceConfig extends GuiceServletContextListener {
    final static Logger logger = Logger.getLogger(GuiceConfig.class);

    private static class DependencyModule extends AbstractModule {

        @Override
        protected void configure() {
            logger.info("binding interfaces with implementations");

            bind(ConnectionPool.class).to(PgConnectionPool.class).in(Singleton.class);

            bind(UserDao.class).to(PgUserDao.class).in(Singleton.class); //bind(UserDao.class).to(DummyUserDao.class).in(Singleton.class); //
            bind(EducationDao.class).to(PgEducationDao.class).in(Singleton.class);
            bind(CommunityDao.class).to(PgCommunityDao.class).in(Singleton.class);
            bind(ColleaguesDao.class).to(PgColleaguesDao.class).in(Singleton.class);
            bind(MessageDao.class).to(PgMessageDao.class).in(Singleton.class);
            bind(PostDao.class).to(PgPostDao.class).in(Singleton.class);

            bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
            bind(SecurityService.class).to(SecurityServiceImpl.class).in(Singleton.class);
            bind(ProfileService.class).to(ProfileServiceImpl.class).in(Singleton.class);
        }
    }

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets(){
            logger.info("configurations paths with servlets");

            serve("/").with(RootServlet.class);
            serve("/registration").with(RegistrationServlet.class);
            serve("/login").with(LoginServlet.class);
            serve("/logout").with(LogoutServlet.class);
            serve("/locale").with(LocaleServlet.class);
            serve("/profile").with(ProfileServlet.class);
        }
    }

    @Override
    protected Injector getInjector() {
        logger.info("create injector");
        return Guice.createInjector(new DependencyModule(), new ServletConfigModule());
    }
}
