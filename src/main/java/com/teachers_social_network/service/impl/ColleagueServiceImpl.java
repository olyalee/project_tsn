package com.teachers_social_network.service.impl;

import com.teachers_social_network.dao.interfaces.ColleaguesDao;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.ColleagueService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Olya Lee on 10.03.2017.
 */
public class ColleagueServiceImpl implements ColleagueService {
    private final ColleaguesDao colleaguesDao;

    @Inject
    public ColleagueServiceImpl(ColleaguesDao colleaguesDao) {
        this.colleaguesDao = colleaguesDao;
    }

    @Override
    public boolean addColleague(String user_login, String colleague_login) {
        return colleaguesDao.addColleague(user_login, colleague_login);
    }

    @Override
    public boolean removeColleague(String user_login, String colleague_login) {
        return colleaguesDao.removeColleague(user_login,colleague_login);
    }

    @Override
    public boolean isColleague(String user_login, String colleague_login) {
        return colleaguesDao.isColleague(user_login, colleague_login);
    }

    @Override
    public List<User> listOfColleagues(String user_login) {
        return colleaguesDao.listOfColleagues(user_login);
    }
}
