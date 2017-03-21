package com.teachers_social_network.web.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.teachers_social_network.dao.interfaces.*;
import com.teachers_social_network.dao.pg.*;
import com.teachers_social_network.service.impl.*;
import com.teachers_social_network.service.interfaces.*;
import com.teachers_social_network.web.filter.CharsetFilter;
import com.teachers_social_network.web.filter.LoggedInFilter;
import com.teachers_social_network.web.servlet.*;
import org.apache.log4j.Logger;
import com.google.inject.Singleton;

import javax.servlet.annotation.WebListener;

/**
 * Configuration for Guice
 * Contains info about implementations of interfaces, apps servlets and filters.
 */
@WebListener
public class GuiceConfig extends GuiceServletContextListener {
    final static Logger logger = Logger.getLogger(GuiceConfig.class);

    private static class DependencyModule extends AbstractModule {

        @Override
        protected void configure() {

            bind(ConnectionPool.class).to(PgConnectionPool.class).in(Singleton.class);

            bind(UserDao.class).to(PgUserDao.class).in(Singleton.class);
            bind(EducationDao.class).to(PgEducationDao.class).in(Singleton.class);
            bind(CommunityDao.class).to(PgCommunityDao.class).in(Singleton.class);
            bind(ColleaguesDao.class).to(PgColleaguesDao.class).in(Singleton.class);
            bind(MessageDao.class).to(PgMessageDao.class).in(Singleton.class);
            bind(PostDao.class).to(PgPostDao.class).in(Singleton.class);

            bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
            bind(SecurityService.class).to(SecurityServiceImpl.class).in(Singleton.class);
            bind(ColleagueService.class).to(ColleagueServiceImpl.class).in(Singleton.class);
            bind(CommunityService.class).to(CommunityServiceImpl.class).in(Singleton.class);
            bind(MessageService.class).to(MessageServiceImpl.class).in(Singleton.class);
        }
    }

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets(){

            serve("/").with(RootServlet.class);
            serve("/registration").with(RegistrationServlet.class);
            serve("/login").with(LoginServlet.class);
            serve("/jsp/logout").with(LogoutServlet.class);
            serve("/locale").with(LocaleServlet.class);
            serve("/jsp/profile").with(ProfileServlet.class);
            serve("/jsp/colleagues").with(ColleaguesServlet.class);
            serve("/jsp/communities").with(CommunitiesServlet.class);
            serve("/jsp/messages").with(MessagesServlet.class);
            serve("/jsp/otherProfile").with(OtherProfileServlet.class);
        }
    }

    private static class FilterConfigModule extends ServletModule{
        @Override
        protected void configureServlets() {

            filter("/*").through(CharsetFilter.class);
            filter("/jsp/*").through(LoggedInFilter.class);
        }
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new DependencyModule(), new ServletConfigModule(), new FilterConfigModule());
    }
}
