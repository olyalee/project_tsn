package com.teachers_social_network.web.servlet.model;

import lombok.Builder;
import lombok.Value;

/**
 * Created by Olya Lee on 04.02.2017.
 */

@Value
@Builder
public class FieldValidation {
    boolean isEmptyField;
    boolean isIncorrect;
}
