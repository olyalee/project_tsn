package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

/**
 * Created by Olya Lee on 08.02.2017.
 */
@Value
@Builder
public class Post {
    int id;
    int community_id;
    String login;
    String text;
    Timestamp time;
}
