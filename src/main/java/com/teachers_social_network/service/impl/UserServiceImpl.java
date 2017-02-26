package com.teachers_social_network.service.impl;

import com.teachers_social_network.dao.interfaces.UserDao;
import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.SecurityService;
import com.teachers_social_network.service.interfaces.UserService;

import java.sql.Date;
import java.util.Optional;

/**
 * Created by Olya Lee on 04.02.2017.
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final SecurityService securityService;

    public UserServiceImpl(UserDao userDao, SecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }

    @Override
    public Optional<User> addUser(User user) {
        //add hash for pass
        User userWishHash = User.builder()
                .passwordHash(securityService.encrypt(user.getPasswordHash()))
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .country(user.getCountry())
                .city(user.getCity())
                .science_field(user.getScience_field())
                .working_place(user.getWorking_place())
                .position(user.getPosition()).build();
        if(userDao.create(userWishHash)){
         return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByCredentials(Credentials credentials) {
        final Optional<User> userOptional = userDao.getByLogin(credentials.getLogin());

        if(!userOptional.isPresent()){
            return Optional.empty();
        }

        final User user = userOptional.get();

        if(!securityService.validate(credentials.getPassword(),user.getPasswordHash())){
            return Optional.empty();
        }

        return Optional.of(user);
    }
}
