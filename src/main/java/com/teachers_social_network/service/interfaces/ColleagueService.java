package com.teachers_social_network.service.interfaces;

import com.teachers_social_network.model.User;

import java.util.List;

/**
 * Created by Olya Lee on 10.03.2017.
 */
public interface ColleagueService {
    boolean addColleague(String user_login, String colleague_login);

    boolean removeColleague(String user_login, String colleague_login);

    boolean isColleague(String user_login, String colleague_login);

    List<User> listOfColleagues(String user_login);
}
