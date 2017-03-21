package com.teachers_social_network.service.interfaces;

import com.teachers_social_network.model.User;

import java.util.List;

/**
 * Colleague Service
 * Help to add and remove colleague for user, to check is users are colleague and to get lists of user's colleagues
 */
public interface ColleagueService {
    boolean addColleague(String user_login, String colleague_login);

    boolean removeColleague(String user_login, String colleague_login);

    boolean isColleague(String user_login, String colleague_login);

    List<User> listOfColleagues(String user_login);
}
