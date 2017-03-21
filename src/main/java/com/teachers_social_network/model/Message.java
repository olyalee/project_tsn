package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

/**
 * Message DTO
 */

@Value
@Builder
public class Message {
    int id;
    String from_user;
    String to_user;
    String text;
    Timestamp time;
}
