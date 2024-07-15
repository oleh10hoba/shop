package com.example.shop.ui;

import com.example.shop.application.query.IItemQuery;
import com.example.shop.application.service.ItemService;
import com.example.shop.domain.GridResult;
import com.example.shop.ui.dto.request.*;

import com.example.shop.ui.dto.response.ItemResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@OpenAPIDefinition
public class ItemController {
    private final ItemService itemService;
    private final IItemQuery itemQuery;

    @ApiOperation("Add item to shop")
    @PostMapping(value = "/add")
    public ResponseEntity<Void> addItem(@Valid AddItemRequestDto dto) {
        itemService.add(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Add item list to shop")
    @PostMapping(value = "/list/add")
    public ResponseEntity<Void> addItemList(@Valid @RequestBody AddItemListRequestDto dto) {
        dto.getItems().forEach(item -> itemService.add(item));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Get a list of items from shop")
    @GetMapping(value = "/list")
    public ResponseEntity<GridResult<ItemResponseDto>> itemList(@RequestParam(defaultValue = "10") int pageSize,
                                                                    @RequestParam(defaultValue = "0") int pageNumber ) {
        return ResponseEntity.ok(itemQuery.getItemList(pageSize, pageNumber));
    }

    @ApiOperation("Get item from shop")
    @GetMapping(value = "/get")
    public ResponseEntity<ItemResponseDto> getItem(@Valid @RequestBody GetItemRequestDto dto) {
        return ResponseEntity.ok(itemQuery.getItem(dto.getId()));
    }


    @ApiOperation("Delete item from shop")
    @PostMapping(value = "/delete")
    public ResponseEntity<Void> deleteItem(@Valid @RequestBody DeleteItemRequestDto dto) {
        itemQuery.deleteById(dto.getId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Update item")
    @PostMapping(value = "/update")
    public ResponseEntity<Void> updateItem(@Valid @RequestBody UpdateItemRequestDto dto) {
        itemService.update(dto);
        return ResponseEntity.ok().build();
    }
}
