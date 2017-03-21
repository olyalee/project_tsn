package com.teachers_social_network.web.servlet.model;

import lombok.Builder;
import lombok.Value;

/**
 * Field Validation
 */

@Value
@Builder
public class FieldValidation {
    boolean isEmptyField;
    boolean isIncorrect;
}
