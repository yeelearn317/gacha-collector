package com.gacha.service.impl;

import com.gacha.dto.ItemDTO;
import com.gacha.dto.ItemRequestDTO;
import com.gacha.dto.ItemResponseDTO;
import com.gacha.entity.Item;
import com.gacha.entity.Series;
import com.gacha.repository.ItemRepository;
import com.gacha.repository.SeriesRepository;
import com.gacha.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final SeriesRepository seriesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDTO> findById(Long id) {
        return itemRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public ItemDTO save(ItemDTO dto) {
        Item item = toEntity(dto);
        return toDTO(itemRepository.save(item));
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> findAllWithDetails() {
        return itemRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> findBySeriesId(Long seriesId) {
        return itemRepository.findBySeriesId(seriesId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ItemResponseDTO findByIdWithDetails(Long id) {
        return itemRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDTO findByJanCode(String janCode) {
        return Optional.ofNullable(itemRepository.findByJanCode(janCode))
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public ItemDTO create(ItemRequestDTO request) {
        Series series = seriesRepository.findById(request.getSeriesId())
                .orElseThrow(() -> new RuntimeException("Series not found"));

        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setJanCode(request.getJanCode());
        item.setRarity(request.getRarity());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setSeries(series);
        return toDTO(itemRepository.save(item));
    }

    @Override
    public ItemDTO update(Long id, ItemRequestDTO request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        Series series = seriesRepository.findById(request.getSeriesId())
                .orElseThrow(() -> new RuntimeException("Series not found"));

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setJanCode(request.getJanCode());
        item.setRarity(request.getRarity());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setSeries(series);
        return toDTO(itemRepository.save(item));
    }

    private ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setJanCode(item.getJanCode());
        dto.setRarity(item.getRarity());
        dto.setPrice(item.getPrice());
        dto.setImageUrl(item.getImageUrl());
        dto.setSeriesId(item.getSeries().getId());
        return dto;
    }

    private Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setJanCode(dto.getJanCode());
        item.setRarity(dto.getRarity());
        item.setPrice(dto.getPrice());
        item.setImageUrl(dto.getImageUrl());
        return item;
    }

    private ItemResponseDTO toResponseDTO(Item item) {
        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setJanCode(item.getJanCode());
        dto.setRarity(item.getRarity());
        dto.setPrice(item.getPrice());
        dto.setImageUrl(item.getImageUrl());
        dto.setSeriesId(item.getSeries().getId());
        dto.setSeriesName(item.getSeries().getName());
        dto.setBrandName(item.getSeries().getBrand().getName());
        return dto;
    }
} 