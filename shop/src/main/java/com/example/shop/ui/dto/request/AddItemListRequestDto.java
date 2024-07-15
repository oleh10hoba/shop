package com.example.shop.ui.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddItemListRequestDto {
    @Valid
    private List<AddItemRequestDto> items;
}