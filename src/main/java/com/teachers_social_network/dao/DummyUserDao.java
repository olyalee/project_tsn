package com.teachers_social_network.dao;

import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.UserDao;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.SecurityService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.Date;
import java.util.Optional;

/**
 * Created by Olya Lee on 08.03.2017.
 */
public class DummyUserDao implements UserDao {
    final static Logger logger = Logger.getLogger(DummyUserDao.class);

    private final ConnectionPool connectionPool;
    private final SecurityService securityService;

    @Inject
    public DummyUserDao(ConnectionPool connectionPool, SecurityService securityService) {
        this.connectionPool = connectionPool;
        this.securityService = securityService;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        if(!"mark".equals(login)){
            return Optional.empty();
        }
        return Optional.of(
                User.builder()
                        .login("mark")
                        .passwordHash(securityService.encrypt("test"))
                .firstName("Mark")
                .lastName("Markov")
                .gender(Gender.MALE)
                .birthDate(Date.valueOf("1978-09-01"))
                .email("mark@gmail.com")
                .city("Saint-Petersburg")
                .country("Russia")
                .working_place("SPbGU")
                .position("lecturer")
                .science_field("Astronomy").build()
        );
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(String login) {
        return false;
    }
}
