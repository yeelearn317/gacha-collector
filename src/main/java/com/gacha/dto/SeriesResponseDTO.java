package com.gacha.dto;

import lombok.Data;

@Data
public class SeriesResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long brandId;
    private String brandName;
    private String releaseMonth;
    private Integer itemCount;
} 