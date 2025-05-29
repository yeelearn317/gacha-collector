package com.gacha.service;

import com.gacha.dto.SeriesDTO;
import com.gacha.dto.SeriesRequestDTO;
import com.gacha.dto.SeriesResponseDTO;
import java.util.List;

public interface SeriesService extends CrudService<SeriesDTO, Long> {
    List<SeriesResponseDTO> findAllWithItemCount();
    List<SeriesResponseDTO> findByBrandId(Long brandId);
    SeriesResponseDTO findByIdWithItemCount(Long id);
    SeriesDTO create(SeriesRequestDTO request);
    SeriesDTO update(Long id, SeriesRequestDTO request);
} 