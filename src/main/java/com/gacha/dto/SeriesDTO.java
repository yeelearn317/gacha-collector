package com.gacha.dto;

import lombok.Data;

@Data
public class SeriesDTO {
    private Long id;
    private String name;
    private String description;
    private Long brandId;
    private String releaseMonth;
} 