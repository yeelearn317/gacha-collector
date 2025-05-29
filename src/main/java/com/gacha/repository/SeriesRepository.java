package com.gacha.repository;

import com.gacha.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByBrandId(Long brandId);
    List<Series> findByNameContaining(String name);
} 