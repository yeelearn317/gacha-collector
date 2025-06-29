package com.gacha.dto;

import lombok.Data;

@Data
public class ItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String janCode;
    private String rarity;
    private Double price;
    private String imageUrl;
    private Long seriesId;
    private String seriesName;
    private String brandName;
} 