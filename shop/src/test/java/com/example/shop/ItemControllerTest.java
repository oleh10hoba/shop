package com.example.shop;

import com.example.shop.application.query.IItemQuery;
import com.example.shop.application.service.ItemService;
import com.example.shop.domain.GridResult;
import com.example.shop.ui.ItemController;
import com.example.shop.ui.dto.request.*;
import com.example.shop.ui.dto.response.ItemResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @Mock
    private IItemQuery itemQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        AddItemRequestDto dto = new AddItemRequestDto("Name", 15.4);
        doNothing().when(itemService).add(dto);

        ResponseEntity<Void> response = itemController.addItem(dto);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(itemService, times(1)).add(dto);
    }

    @Test
    void addItemList() {
        AddItemListRequestDto dto = new AddItemListRequestDto();
        dto.setItems(new ArrayList<>());
        doNothing().when(itemService).add(any(AddItemRequestDto.class));

        ResponseEntity<Void> response = itemController.addItemList(dto);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(itemService, times(dto.getItems().size())).add(any(AddItemRequestDto.class));
    }

    @Test
    void itemList() {
        GridResult<ItemResponseDto> gridResult = new GridResult<>(null, 0, 0, 0);
        when(itemQuery.getItemList(10, 0)).thenReturn(gridResult);

        ResponseEntity<GridResult<ItemResponseDto>> response = itemController.itemList(10, 0);

        assertEquals(ResponseEntity.ok(gridResult), response);
        verify(itemQuery, times(1)).getItemList(10, 0);
    }

    @Test
    void getItem() {
        GetItemRequestDto dto = new GetItemRequestDto();
        dto.setId(UUID.randomUUID());
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        when(itemQuery.getItem(dto.getId())).thenReturn(itemResponseDto);

        ResponseEntity<ItemResponseDto> response = itemController.getItem(dto);

        assertEquals(ResponseEntity.ok(itemResponseDto), response);
        verify(itemQuery, times(1)).getItem(dto.getId());
    }

    @Test
    void deleteItem() {
        DeleteItemRequestDto dto = new DeleteItemRequestDto();
        dto.setId(UUID.randomUUID());
        doNothing().when(itemQuery).deleteById(dto.getId());

        ResponseEntity<Void> response = itemController.deleteItem(dto);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(itemQuery, times(1)).deleteById(dto.getId());
    }

    @Test
    void updateItem() {
        UpdateItemRequestDto dto = new UpdateItemRequestDto();
        doNothing().when(itemService).update(dto);

        ResponseEntity<Void> response = itemController.updateItem(dto);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(itemService, times(1)).update(dto);
    }
}
