package com.example.shop.application.query;

import com.example.shop.domain.GridResult;
import com.example.shop.ui.dto.response.ItemResponseDto;

import java.util.UUID;

public interface IItemQuery {

    boolean existsByName(String name);

    boolean isNameNotAvailable(String name, UUID id);

    boolean existsById(UUID id);

    GridResult<ItemResponseDto> getItemList(int pageSize, int pageNumber);

    ItemResponseDto getItem(UUID id);

    void deleteById(UUID id);
}