package com.gacha.service;

import com.gacha.dto.ItemDTO;
import com.gacha.dto.ItemRequestDTO;
import com.gacha.dto.ItemResponseDTO;
import java.util.List;

public interface ItemService extends CrudService<ItemDTO, Long> {
    List<ItemResponseDTO> findAllWithDetails();
    List<ItemResponseDTO> findBySeriesId(Long seriesId);
    ItemResponseDTO findByIdWithDetails(Long id);
    ItemDTO findByJanCode(String janCode);
    ItemDTO create(ItemRequestDTO request);
    ItemDTO update(Long id, ItemRequestDTO request);
} 