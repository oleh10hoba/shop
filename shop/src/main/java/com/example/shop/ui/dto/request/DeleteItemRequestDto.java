package com.example.shop.ui.dto.request;

import com.example.shop.ui.validator.ItemIdConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class DeleteItemRequestDto {
    @ItemIdConstraint
    private UUID id;
}