# 使用 Docker Compose 啟動 PostgreSQL 與 MongoDB

## 啟動指令

在專案根目錄（有 `docker-compose.yml` 的地方）執行：

```sh
docker-compose up -d
```

- `up`：啟動所有在 docker-compose.yml 定義的服務（這裡是 postgres 和 mongodb）
- `-d`：讓服務在背景執行（detached mode）

---

## 常用 Docker Compose 指令

- **查看服務狀態**
  ```sh
  docker-compose ps
  ```

- **停止所有服務**
  ```sh
  docker-compose down
  ```

- **查看即時 log**
  ```sh
  docker-compose logs -f
  ```

---

只要執行 `docker-compose up -d`，你的 PostgreSQL 和 MongoDB 就會自動啟動並持續運作！
