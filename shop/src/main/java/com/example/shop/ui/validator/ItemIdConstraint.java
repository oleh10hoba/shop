package com.example.shop.ui.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ItemIdValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemIdConstraint {
    String message() default "Invalid item id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}