package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

/**
 * Created by Olya Lee on 08.01.2017.
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
