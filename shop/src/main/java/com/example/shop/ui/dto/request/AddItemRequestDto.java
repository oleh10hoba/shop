package com.example.shop.ui.dto.request;

import com.example.shop.ui.validator.ItemNameConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@Value
public class AddItemRequestDto {
    @ItemNameConstraint
    private String name;
    @NotNull(message = "error.price_required")
    @Range(min = 0, message = "error.price_cannot_be_less_than_zero")
    private Double price;
}