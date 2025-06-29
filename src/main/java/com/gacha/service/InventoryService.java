package com.gacha.service;

import com.gacha.entity.Inventory;
import java.util.List;

public interface InventoryService {
    Inventory addToInventory(Long itemId, String photoUrl);
    Inventory updateQuantity(String inventoryId, Integer quantity);
    void removeFromInventory(String inventoryId);
    Inventory findByItemId(Long itemId);
    List<Inventory> findByItemIds(List<Long> itemIds);
    List<Inventory> findAll();
} 