package com.teachers_social_network.model;

import com.teachers_social_network.model.Gender;
import lombok.Builder;
import lombok.Value;

import java.sql.Date;

/**
 * User DTO
 */
@Value
@Builder
public class User {
    String login;
    String passwordHash;
    String firstName;
    String lastName;
    Gender gender;
    Date birthDate;
    String email;
    String country;
    String city;
    String science_field;
    String working_place;
    String position;
}
