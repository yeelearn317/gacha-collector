package com.gacha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "inventory")
public class Inventory {
    @Id
    private String id;

    private Long itemId;
    private Integer quantity;
    private String photoUrl;  // 實拍照URL
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 