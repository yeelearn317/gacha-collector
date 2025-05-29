package com.gacha.service.impl;

import com.gacha.dto.SeriesDTO;
import com.gacha.dto.SeriesRequestDTO;
import com.gacha.dto.SeriesResponseDTO;
import com.gacha.entity.Brand;
import com.gacha.entity.Series;
import com.gacha.repository.BrandRepository;
import com.gacha.repository.SeriesRepository;
import com.gacha.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;
    private final BrandRepository brandRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SeriesDTO> findAll() {
        return seriesRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SeriesDTO> findById(Long id) {
        return seriesRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public SeriesDTO save(SeriesDTO dto) {
        Series series = toEntity(dto);
        return toDTO(seriesRepository.save(series));
    }

    @Override
    public void deleteById(Long id) {
        seriesRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeriesResponseDTO> findAllWithItemCount() {
        return seriesRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeriesResponseDTO> findByBrandId(Long brandId) {
        return seriesRepository.findByBrandId(brandId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SeriesResponseDTO findByIdWithItemCount(Long id) {
        return seriesRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Series not found"));
    }

    @Override
    public SeriesDTO create(SeriesRequestDTO request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Series series = new Series();
        series.setName(request.getName());
        series.setDescription(request.getDescription());
        series.setBrand(brand);
        series.setReleaseMonth(request.getReleaseMonth());
        return toDTO(seriesRepository.save(series));
    }

    @Override
    public SeriesDTO update(Long id, SeriesRequestDTO request) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        series.setName(request.getName());
        series.setDescription(request.getDescription());
        series.setBrand(brand);
        series.setReleaseMonth(request.getReleaseMonth());
        return toDTO(seriesRepository.save(series));
    }

    private SeriesDTO toDTO(Series series) {
        SeriesDTO dto = new SeriesDTO();
        dto.setId(series.getId());
        dto.setName(series.getName());
        dto.setDescription(series.getDescription());
        dto.setBrandId(series.getBrand().getId());
        dto.setReleaseMonth(series.getReleaseMonth());
        return dto;
    }

    private Series toEntity(SeriesDTO dto) {
        Series series = new Series();
        series.setId(dto.getId());
        series.setName(dto.getName());
        series.setDescription(dto.getDescription());
        series.setReleaseMonth(dto.getReleaseMonth());
        return series;
    }

    private SeriesResponseDTO toResponseDTO(Series series) {
        SeriesResponseDTO dto = new SeriesResponseDTO();
        dto.setId(series.getId());
        dto.setName(series.getName());
        dto.setDescription(series.getDescription());
        dto.setBrandId(series.getBrand().getId());
        dto.setBrandName(series.getBrand().getName());
        dto.setReleaseMonth(series.getReleaseMonth());
        dto.setItemCount(series.getItems() != null ? series.getItems().size() : 0);
        return dto;
    }
} 