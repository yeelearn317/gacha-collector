package com.gacha.repository;

import com.gacha.entity.Series;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Series資料庫訪問層的測試類
 *
 * @DataJpaTest: 提供JPA測試的配置，會啟動內嵌的資料庫
 * @AutoConfigureTestDatabase: 配置測試資料庫的替換策略
 *    - Replace.NONE: 不替換資料庫，使用實際配置的外部資料庫
 * @TestPropertySource: 提供測試專用的屬性配置
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(properties = {
    // 這個屬性會在測試執行完後刪除所有表格，確保測試環境的乾淨
    "spring.jpa.hibernate.ddl-auto=create-drop",
    // 這個屬性會讓Spring Boot總是執行SQL初始化腳本(data.sql)
    "spring.sql.init.mode=always",
    // 這個屬性會讓Spring等Hibernate建完表後再執行data.sql
    // 沒有這個設定，data.sql可能在表格建立前執行，導致錯誤
    "spring.jpa.defer-datasource-initialization=true"
})
class SeriesRepositoryTest {
    @Autowired
    SeriesRepository repo;
    
    /**
     * 測試根據品牌ID查詢系列的方法
     * 
     * 測試執行流程:
     * 1. Spring Boot啟動測試環境，根據實體類創建表結構
     * 2. 執行src/main/resources/data.sql，插入種子資料
     * 3. 執行測試方法，調用findByBrandId查詢
     * 4. 測試結束後，刪除所有表格
     */
    @Test
    void shouldFindByBrand() {
        List<Series> series = repo.findByBrandId(1L);
        System.out.println("查詢結果: " + series.size() + " 條記錄");
        for (Series s : series) {
            System.out.println("Series: " + s.getName() + ", Brand ID: " + s.getBrand().getId());
        }
        assertThat(series).hasSizeGreaterThan(0);
    }
} 