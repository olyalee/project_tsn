package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Community DTO
 */
@Value
@Builder
public class Community {
    int id;
    String title;
    List<User> members;
}
