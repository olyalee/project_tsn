package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

/**
 * Post DTO
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
