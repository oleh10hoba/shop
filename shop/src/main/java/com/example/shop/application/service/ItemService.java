package com.example.shop.application.service;

import com.example.shop.domain.Item;
import com.example.shop.infrastructure.repository.JpaItemRepository;
import com.example.shop.ui.dto.request.AddItemRequestDto;
import com.example.shop.ui.dto.request.UpdateItemRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemService {

    private final JpaItemRepository jpaItemRepository;

    @Transactional
    @CachePut(value = "items", key = "#item.id")
    public void add(AddItemRequestDto dto) {
        Item item = Item.add(
                UUID.randomUUID(),
                dto.getName(),
                dto.getPrice(),
                Instant.now());
        jpaItemRepository.save(item);
    }

    @Transactional
    @CachePut(value = "items", key = "#item.id")
    public void update(UpdateItemRequestDto dto) {
        Item item = jpaItemRepository.getReferenceById(dto.getId());
        item.update(dto.getName(),
                dto.getPrice(),
                Instant.now());
        jpaItemRepository.save(item);
    }
}