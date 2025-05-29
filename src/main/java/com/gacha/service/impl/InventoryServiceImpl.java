package com.gacha.service.impl;

import com.gacha.entity.Inventory;
import com.gacha.repository.InventoryRepository;
import com.gacha.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory addToInventory(Long itemId, String photoUrl) {
        Inventory existing = inventoryRepository.findByItemId(itemId);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
            existing.onUpdate();
            return inventoryRepository.save(existing);
        }

        Inventory inventory = new Inventory();
        inventory.setItemId(itemId);
        inventory.setQuantity(1);
        inventory.setPhotoUrl(photoUrl);
        inventory.onCreate();
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateQuantity(String inventoryId, Integer quantity) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
            .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(quantity);
        inventory.onUpdate();
        return inventoryRepository.save(inventory);
    }

    @Override
    public void removeFromInventory(String inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }

    @Override
    public Inventory findByItemId(Long itemId) {
        return inventoryRepository.findByItemId(itemId);
    }

    @Override
    public List<Inventory> findByItemIds(List<Long> itemIds) {
        return inventoryRepository.findByItemIdIn(itemIds);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }
} 