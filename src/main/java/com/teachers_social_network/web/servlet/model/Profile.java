package com.teachers_social_network.web.servlet.model;

import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.User;
import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Created by Olya Lee on 08.03.2017.
 */
@Value
@Builder
public class Profile {
    User user;
    List<Education> educations;
}
