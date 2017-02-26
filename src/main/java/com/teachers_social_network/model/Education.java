package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

/**
 * Created by Olya Lee on 08.01.2017.
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
