# 資料庫結構文件

## 概述
本文檔描述了扭蛋收藏家應用程式的資料庫結構。應用程式使用兩個資料庫：
- PostgreSQL 用於關聯式資料（品牌、系列、商品）
- MongoDB 用於庫存管理

## PostgreSQL 資料表

### brands（品牌）
儲存不同扭蛋品牌的資訊。

| 欄位 | 類型 | 約束條件 | 說明 |
|--------|------|-------------|-------------|
| id | BIGINT | 主鍵，自動遞增 | 品牌的唯一識別碼 |
| name | VARCHAR | 非空，唯一 | 品牌名稱（例如："GSC"、"Taito"） |
| description | TEXT | 可空 | 品牌的詳細描述 |

### series（系列）
儲存品牌下的扭蛋系列資訊。

| 欄位 | 類型 | 約束條件 | 說明 |
|--------|------|-------------|-------------|
| id | BIGINT | 主鍵，自動遞增 | 系列的唯一識別碼 |
| name | VARCHAR | 非空 | 系列名稱 |
| description | TEXT | 可空 | 系列的詳細描述 |
| brand_id | BIGINT | 外鍵，非空 | 所屬品牌的參考 |
| release_month | VARCHAR | 可空 | 系列發售月份 |

### items（商品）
儲存個別扭蛋商品的資訊。

| 欄位 | 類型 | 約束條件 | 說明 |
|--------|------|-------------|-------------|
| id | BIGINT | 主鍵，自動遞增 | 商品的唯一識別碼 |
| name | VARCHAR | 非空 | 商品名稱 |
| description | TEXT | 可空 | 商品的詳細描述 |
| jan_code | VARCHAR | 唯一 | 日本商品條碼（JAN碼） |
| rarity | VARCHAR | 可空 | 稀有度（普通、稀有、隱藏） |
| price | DECIMAL | 可空 | 商品價格 |
| image_url | VARCHAR | 可空 | 商品圖片網址 |
| series_id | BIGINT | 外鍵，非空 | 所屬系列的參考 |

## MongoDB 集合

### inventory（庫存）
儲存使用者的庫存商品。

| 欄位 | 類型 | 說明 |
|-------|------|-------------|
| id | String | MongoDB 文件識別碼 |
| itemId | Long | PostgreSQL 中商品的參考 |
| quantity | Integer | 擁有數量 |
| photoUrl | String | 使用者拍攝的商品照片網址 |
| createdAt | DateTime | 加入庫存的時間 |
| updatedAt | DateTime | 最後更新時間 |

## 關聯關係

1. **品牌到系列**
   - 一對多關係
   - 一個品牌可以有多個系列
   - 每個系列屬於一個品牌

2. **系列到商品**
   - 一對多關係
   - 一個系列可以有多個商品
   - 每個商品屬於一個系列

3. **商品到庫存**
   - 一對多關係（跨資料庫）
   - 一個商品可以有多個庫存記錄
   - 每個庫存記錄對應一個商品

## 索引

### PostgreSQL
- brands: name（唯一索引）
- items: jan_code（唯一索引）
- series: brand_id（外鍵索引）
- items: series_id（外鍵索引）

### MongoDB
- inventory: itemId（索引）
- inventory: createdAt（索引）
- inventory: updatedAt（索引）

## 注意事項
- 所有時間戳記均以 UTC 時間儲存
- MongoDB 使用字串型態的 ID，而 PostgreSQL 使用長整數型態的 ID
- 庫存集合使用 MongoDB 以獲得更好的擴展性和靈活性，適合儲存使用者特定的資料 