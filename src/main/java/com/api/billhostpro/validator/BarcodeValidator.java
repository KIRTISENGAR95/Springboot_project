package com.api.billhostpro.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class BarcodeValidator implements ConstraintValidator<ValidBarcode, String> {

    private static final Pattern BARCODE_PATTERN =
            Pattern.compile("^[A-Za-z0-9]{5,30}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return BARCODE_PATTERN.matcher(value).matches();
    }

}
