package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.Education;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olya Lee on 11.02.2017.
 */
public interface EducationDao {
    /**add new row to the DB*/
    boolean create(Education education);

    /**return all user's educations*/
    List<Education> getByLogin(String login);

    /**update education in DB*/
    boolean update(Education education);

    /**delete education from DB*/
    boolean delete(Education education);

    Optional<Education> getById(int id);
}
