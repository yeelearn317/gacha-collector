package com.gacha.repository;

import com.gacha.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySeriesId(Long seriesId);
    Item findByJanCode(String janCode);
    List<Item> findByNameContaining(String name);
} 