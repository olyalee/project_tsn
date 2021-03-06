package com.teachers_social_network.service.interfaces;

import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


/**
 * User Service
 * Help to make operations related to user, user profile and education
 */
public interface UserService {
    Optional<User> addUser(User user);
    Optional<User> getByCredentials(Credentials credentials);

    boolean updateUser(User user);

    User getByLogin(String login);

    List<Education> getEducationByLogin(String login);
    boolean updateEducation(Education education);
    boolean addEducation(Education education);
    boolean deleteEducation(int id);

    Date parseDate(String stringDate);
    Gender parseGender(String stringGender);
}
