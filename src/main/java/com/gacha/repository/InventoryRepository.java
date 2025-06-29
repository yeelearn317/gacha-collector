package com.gacha.repository;

import com.gacha.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Inventory findByItemId(Long itemId);
    List<Inventory> findByItemIdIn(List<Long> itemIds);
    void deleteByItemId(Long itemId);
} 