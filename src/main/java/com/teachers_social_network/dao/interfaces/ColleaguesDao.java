package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.User;

import java.util.List;

/**
 * DAO for Colleagues Entity
 */
public interface ColleaguesDao {

    /**add new colleague to user*/
    boolean addColleague(String user_login, String colleague_login);

    /**remove colleague from user*/
    boolean removeColleague(String user_login, String colleague_login);

    /**check is one user is colleague to other */
    boolean isColleague(String user_login, String colleague_login);

    /**return all colleagues for user */
    List<User> listOfColleagues(String user_login);
}
