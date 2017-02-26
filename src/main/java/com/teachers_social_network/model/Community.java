package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Created by Olya Lee on 07.02.2017.
 */
@Value
@Builder
public class Community {
    int id;
    String title;
    List<User> members;
}
