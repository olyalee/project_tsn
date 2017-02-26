package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.User;

import java.util.Optional;

/**
 * Created by Olya Lee on 04.02.2017.
 */
public interface UserDao {
    /**return user if he exist in the DB*/
    Optional<User> getByLogin(String login);

    /**add new row to the DB's table which represents object*/
    boolean create(User user);

    /**update user's data in DB*/
    boolean update(User user);

    /**delete user from DB*/
    boolean delete(String login);
}
