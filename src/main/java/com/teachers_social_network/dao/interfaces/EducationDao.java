package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.Education;

import java.util.List;
import java.util.Optional;

/**
 * DAO for Education Entity
 */
public interface EducationDao {
    /**add new education to the DB*/
    boolean create(Education education);

    /**return all user's educations*/
    List<Education> getByLogin(String login);

    /**update education in DB*/
    boolean update(Education education);

    /**delete education from DB*/
    boolean delete(Education education);

    /**get optional of education by education's id*/
    Optional<Education> getById(int id);
}
