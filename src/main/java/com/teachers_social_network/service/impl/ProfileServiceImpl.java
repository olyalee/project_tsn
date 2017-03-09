package com.teachers_social_network.service.impl;

import com.teachers_social_network.dao.interfaces.EducationDao;
import com.teachers_social_network.model.Education;
import com.teachers_social_network.service.interfaces.ProfileService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Olya Lee on 08.03.2017.
 */
public class ProfileServiceImpl implements ProfileService {
    final static Logger logger = Logger.getLogger(ProfileServiceImpl.class);

    private final EducationDao educationDao;

    @Inject
    public ProfileServiceImpl(EducationDao educationDao) {
        this.educationDao = educationDao;
    }

    @Override
    public List<Education> getEducationByLogin(String login) {
        List<Education> educations = educationDao.getByLogin(login);
        return educations;
    }
}
