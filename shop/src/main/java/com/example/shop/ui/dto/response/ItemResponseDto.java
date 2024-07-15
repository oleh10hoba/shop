package com.example.shop.ui.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemResponseDto {
    private UUID id;
    private String name;
    private Double price;
    private Instant createdAt;
    private Instant updatedAt;
}
