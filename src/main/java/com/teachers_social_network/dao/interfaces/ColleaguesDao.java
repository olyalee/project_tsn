package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olya Lee on 11.02.2017.
 */
public interface ColleaguesDao {
    /**add new colleague to user*/
    boolean addColleague(String user_login, String colleague_login);

    /**remove colleague from user*/
    boolean removeColleague(String user_login, String colleague_login);

    /** */
    boolean isColleague(String user_login, String colleague_login);

    /**return all colleagues for user */
    List<User> listOfColleagues(String user_login);
}
