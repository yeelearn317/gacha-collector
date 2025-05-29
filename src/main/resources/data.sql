-- data.sql: Spring Boot自動執行的資料初始化腳本
-- 執行時機:
-- 1. 應用啟動時，由Spring Boot的DataSourceInitializer自動執行
-- 2. 在測試中，需設定spring.jpa.defer-datasource-initialization=true
--    確保Hibernate先建表後，再執行此腳本
-- 3. 此腳本的執行不會在日誌中顯示SQL語句(這是為什麼你看不到INSERT語句的原因)

-- 品牌資料
INSERT INTO brands (id,name) VALUES (1,'Bandai'),(2,'TTA'),(3,'Kitan');

-- 系列資料
INSERT INTO series (brand_id,name,release_month) VALUES (1,'動物星球立體模型 Vol.3','2025-05-01');

-- 商品資料
INSERT INTO items (series_id,name,rarity,jan_code,image_url) VALUES (1,'白獅', 'COMMON','4549660-123456','https://example.com/lion.png'); 