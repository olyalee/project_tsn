package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.User;

import java.util.Optional;

/**
 * DAO for Users Entity
 */
public interface UserDao {
    /**return user if he exist in the DB*/
    Optional<User> getByLogin(String login);

    /**add new user to the DB*/
    boolean create(User user);

    /**update user's data in DB*/
    boolean update(User user);

    /**delete user from DB*/
    boolean delete(String login);
}
