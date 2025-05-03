package com.gacha.repository;

import com.gacha.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Brand資料庫訪問層的測試類
 *
 * Spring Boot測試的資料初始化機制:
 * 1. 應用啟動時，DataSourceInitializer類負責初始化數據
 * 2. 它會自動掃描classpath中的schema.sql和data.sql
 * 3. schema.sql用於定義表結構，data.sql用於插入初始數據
 * 4. 實際執行順序: 建表(Hibernate) -> 插入數據(data.sql) -> 執行測試 -> 刪表(Hibernate)
 *
 * 為什麼測試日誌中看不到INSERT語句:
 * - Hibernate的SQL語句會在啟用show-sql時顯示
 * - 但data.sql的INSERT語句由Spring的DataSourceInitializer執行，不會顯示在日誌中
 * - 這就是為什麼你看到了CREATE TABLE語句，但沒看到INSERT語句
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.sql.init.mode=always",
    "spring.jpa.defer-datasource-initialization=true"
})
class BrandRepositoryTest {
    @Autowired
    BrandRepository repo;
    
    /**
     * 測試查詢所有品牌的方法
     *
     * data.sql的執行時機:
     * 在@DataJpaTest中，Spring Boot會在Hibernate創建表格後
     * 自動執行src/main/resources/data.sql文件
     * 因為設置了spring.jpa.defer-datasource-initialization=true
     */
    @Test
    void shouldFindAllBrands() {
        List<Brand> brands = repo.findAll();
        System.out.println("查詢到的Brand數量: " + brands.size());
        for (Brand brand : brands) {
            System.out.println("Brand ID: " + brand.getId() + ", Name: " + brand.getName());
        }
        assertThat(brands).isNotEmpty();
    }
} 