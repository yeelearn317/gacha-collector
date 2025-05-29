package com.gacha.service;

import com.gacha.dto.BrandDTO;
import com.gacha.dto.BrandRequestDTO;
import com.gacha.dto.BrandResponseDTO;
import java.util.List;

public interface BrandService extends CrudService<BrandDTO, Long> {
    List<BrandResponseDTO> findAllWithSeriesCount();
    BrandResponseDTO findByIdWithSeriesCount(Long id);
    BrandDTO create(BrandRequestDTO request);
    BrandDTO update(Long id, BrandRequestDTO request);
} 