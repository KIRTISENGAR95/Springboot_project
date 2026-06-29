package com.api.billhostpro.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class HsnCodeValidator implements ConstraintValidator<ValidHsnCode, String> {

    private static final Pattern HSN_CODE_PATTERN = Pattern.compile("^\\d{4,8}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return HSN_CODE_PATTERN.matcher(value).matches();
    }

}

