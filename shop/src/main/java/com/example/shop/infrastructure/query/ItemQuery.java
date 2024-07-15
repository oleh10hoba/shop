package com.example.shop.infrastructure.query;

import com.example.shop.application.query.IItemQuery;
import com.example.shop.domain.GridResult;
import com.example.shop.domain.Item;
import com.example.shop.infrastructure.repository.JpaItemRepository;
import com.example.shop.ui.dto.response.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemQuery implements IItemQuery {

    private final JpaItemRepository jpaItemRepository;

    @Override
    public boolean existsByName(String name) {
        return jpaItemRepository.existsByName(name);
    }

    @Override
    public boolean isNameNotAvailable(String name, UUID id) {
        return jpaItemRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaItemRepository.existsById(id);
    }

    @Override
    @CacheEvict(value = "items", key = "#id")
    public void deleteById(UUID id) {
        jpaItemRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "items", key = "'itemList_' + #pageSize + '_' + #pageNumber")
    public GridResult<ItemResponseDto> getItemList(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Item> itemPage = jpaItemRepository.findAll(pageable);

        List<ItemResponseDto> shopResponseDtoList = itemPage.getContent()
                .stream()
                .map(item -> mapItem(item))
                .collect(Collectors.toList());

        return new GridResult<>(shopResponseDtoList, itemPage.getNumber(), itemPage.getSize(), itemPage.getTotalElements());
    }

    @Override
    @Cacheable("items")
    public ItemResponseDto getItem(UUID id) {
        return mapItem(jpaItemRepository.findById(id).get());
    }

    private ItemResponseDto mapItem(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}