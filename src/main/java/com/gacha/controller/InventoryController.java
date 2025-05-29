package com.gacha.controller;

import com.gacha.entity.Inventory;
import com.gacha.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/{itemId}")
    public ResponseEntity<Inventory> addToInventory(
            @PathVariable Long itemId,
            @RequestParam(required = false) String photoUrl) {
        return ResponseEntity.ok(inventoryService.addToInventory(itemId, photoUrl));
    }

    @PutMapping("/{inventoryId}/quantity")
    public ResponseEntity<Inventory> updateQuantity(
            @PathVariable String inventoryId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.updateQuantity(inventoryId, quantity));
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> removeFromInventory(@PathVariable String inventoryId) {
        inventoryService.removeFromInventory(inventoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Inventory> findByItemId(@PathVariable Long itemId) {
        Inventory inventory = inventoryService.findByItemId(itemId);
        return inventory != null ? ResponseEntity.ok(inventory) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }
} 