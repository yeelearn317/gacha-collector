package com.gacha.dto;

import lombok.Data;

@Data
public class BrandResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer seriesCount;
} 