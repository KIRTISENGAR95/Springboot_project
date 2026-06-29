package com.api.billhostpro.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = HsnCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHsnCode {

    String message() default "HSN Code must contain only digits and be between 4 and 8 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
