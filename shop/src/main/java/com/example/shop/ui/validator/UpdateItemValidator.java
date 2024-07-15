package com.example.shop.ui.validator;

import com.example.shop.application.query.IItemQuery;
import com.example.shop.ui.dto.request.UpdateItemRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UpdateItemValidator implements ConstraintValidator<UpdateItemConstraint, UpdateItemRequestDto> {
    private final IItemQuery itemQuery;
    private final static String ALREADY_EXISTS = "_already_exists";
    private final static String INVALID_LENGTH = "_invalid_length";
    private final static String REQUIRED = "_required";
    private final static String ERROR = "error.";
    private final static String NAME = "name";
    private final static String ID = "id";

    @Override
    public boolean isValid(UpdateItemRequestDto dto, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if (dto.getName() == null || dto.getName().isBlank()) {
            isValid = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + NAME + REQUIRED)
                    .addPropertyNode(NAME)
                    .addConstraintViolation();
        } else if (dto.getName().length() > 255) {
            isValid = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + NAME + INVALID_LENGTH)
                    .addPropertyNode(NAME)
                    .addConstraintViolation();
        } else if (itemQuery.isNameNotAvailable(dto.getName(), dto.getId())) {
        isValid = false;
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(ERROR + NAME + ALREADY_EXISTS)
                .addPropertyNode(NAME)
                .addConstraintViolation();
        }

        return isValid;
    }
}