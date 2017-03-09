package com.teachers_social_network.service.interfaces;

import com.teachers_social_network.model.Education;

import java.util.List;

/**
 * Created by Olya Lee on 08.03.2017.
 */
public interface ProfileService {

    List<Education> getEducationByLogin(String login);
}
