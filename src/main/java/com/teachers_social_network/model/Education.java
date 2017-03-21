package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

/**
 * Education DTO
 */

@Value
@Builder
public class Education {
    int id;
    String login;
    String educationType;
    String placeType;
    String placeTitle;
    String major;
    int startYear;
    int endYear;
}
