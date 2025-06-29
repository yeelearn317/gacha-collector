package com.gacha.controller;

import com.gacha.dto.ItemDTO;
import com.gacha.dto.ItemRequestDTO;
import com.gacha.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Tag(name = "商品管理", description = "商品的 CRUD 操作")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @Operation(summary = "取得所有商品")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "取得單一商品")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        return itemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "新增商品")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新商品")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemRequestDTO request) {
        try {
            return ResponseEntity.ok(itemService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除商品")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 