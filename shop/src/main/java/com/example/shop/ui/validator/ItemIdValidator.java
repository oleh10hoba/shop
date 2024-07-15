package com.example.shop.ui.validator;

import com.example.shop.application.query.IItemQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
public class ItemIdValidator implements ConstraintValidator<ItemIdConstraint, UUID> {
    private final IItemQuery itemQuery;
    private final static String NOT_EXISTS = "_not_exists";
    private final static String REQUIRED = "_required";
    private final static String ERROR = "error.";
    private final static String ID = "id";

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if (id == null) {
            isValid = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + ID + REQUIRED)
                    .addConstraintViolation();
        } else if (!itemQuery.existsById(id)) {
                isValid = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + ID + NOT_EXISTS)
                        .addConstraintViolation();
        }

        return isValid;
    }
}