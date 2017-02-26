package com.teachers_social_network.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Credentials {
    String login;
    String password;
}
