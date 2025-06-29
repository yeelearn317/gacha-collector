package com.gacha.service.impl;

import com.gacha.dto.BrandDTO;
import com.gacha.dto.BrandRequestDTO;
import com.gacha.dto.BrandResponseDTO;
import com.gacha.entity.Brand;
import com.gacha.repository.BrandRepository;
import com.gacha.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BrandDTO> findById(Long id) {
        return brandRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public BrandDTO save(BrandRequestDTO request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return toDTO(brandRepository.save(brand));
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponseDTO> findAllWithSeriesCount() {
        return brandRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponseDTO findByIdWithSeriesCount(Long id) {
        return brandRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public BrandDTO create(BrandRequestDTO request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return toDTO(brandRepository.save(brand));
    }

    @Override
    public BrandDTO update(Long id, BrandRequestDTO request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return toDTO(brandRepository.save(brand));
    }

    private BrandDTO toDTO(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        dto.setDescription(brand.getDescription());
        return dto;
    }

    private Brand toEntity(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setId(dto.getId());
        brand.setName(dto.getName());
        brand.setDescription(dto.getDescription());
        return brand;
    }

    private BrandResponseDTO toResponseDTO(Brand brand) {
        BrandResponseDTO dto = new BrandResponseDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        dto.setDescription(brand.getDescription());
        dto.setSeriesCount(brand.getSeries() != null ? brand.getSeries().size() : 0);
        return dto;
    }
} 