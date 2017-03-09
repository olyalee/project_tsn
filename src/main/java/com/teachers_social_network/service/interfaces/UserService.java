package com.teachers_social_network.service.interfaces;

import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.User;

import java.util.Optional;


/**
 * Created by Olya Lee on 04.02.2017.
 */
public interface UserService {
    Optional<User> addUser(User user);
    Optional<User> getByCredentials(Credentials credentials);

    User getByLogin(String login);
}
