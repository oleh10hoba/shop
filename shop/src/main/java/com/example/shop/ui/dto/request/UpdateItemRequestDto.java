package com.example.shop.ui.dto.request;

import com.example.shop.ui.validator.ItemIdConstraint;
import com.example.shop.ui.validator.UpdateItemConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Setter
@Getter
@UpdateItemConstraint
public class UpdateItemRequestDto {
    @ItemIdConstraint
    private UUID id;
    private String name;
    @NotNull(message = "error.price_required")
    @Range(min = 0, message = "error.price_cannot_be_less_than_zero")
    private Double price;
}