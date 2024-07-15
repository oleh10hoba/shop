package com.example.shop.ui.validator;

import com.example.shop.application.query.IItemQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ItemNameValidator implements ConstraintValidator<ItemNameConstraint, String> {
    private final IItemQuery itemQuery;
    private final static String ALREADY_EXISTS = "_already_exists";
    private final static String INVALID_LENGTH = "_invalid_length";
    private final static String REQUIRED = "_required";
    private final static String ERROR = "error.";
    private final static String NAME = "name";

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if (name == null || name.isBlank()) {
            isValid = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + NAME + REQUIRED)
                    .addConstraintViolation();
        } else if (name.length() > 255) {
            isValid = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + NAME + INVALID_LENGTH)
                    .addConstraintViolation();
        } else if (itemQuery.existsByName(name)) {
            isValid = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + NAME + ALREADY_EXISTS)
                    .addConstraintViolation();
        }

        return isValid;
    }
}