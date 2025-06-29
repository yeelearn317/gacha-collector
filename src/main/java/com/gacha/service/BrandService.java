package com.gacha.service;

import com.gacha.dto.BrandDTO;
import com.gacha.dto.BrandRequestDTO;
import com.gacha.dto.BrandResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO> findAll();
    Optional<BrandDTO> findById(Long id);
    BrandDTO save(BrandRequestDTO request);
    BrandDTO update(Long id, BrandRequestDTO request);
    void deleteById(Long id);
    List<BrandResponseDTO> findAllWithSeriesCount();
    BrandResponseDTO findByIdWithSeriesCount(Long id);
    BrandDTO create(BrandRequestDTO request);
} 