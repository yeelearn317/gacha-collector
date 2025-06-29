package com.gacha.controller;

import com.gacha.dto.SeriesDTO;
import com.gacha.dto.SeriesRequestDTO;
import com.gacha.dto.SeriesResponseDTO;
import com.gacha.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;

    @GetMapping
    public ResponseEntity<List<SeriesResponseDTO>> findAll() {
        return ResponseEntity.ok(seriesService.findAllWithItemCount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(seriesService.findByIdWithItemCount(id));
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<SeriesResponseDTO>> findByBrandId(@PathVariable Long brandId) {
        return ResponseEntity.ok(seriesService.findByBrandId(brandId));
    }

    @PostMapping
    public ResponseEntity<SeriesDTO> create(@RequestBody SeriesRequestDTO request) {
        return ResponseEntity.ok(seriesService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesDTO> update(@PathVariable Long id, @RequestBody SeriesRequestDTO request) {
        return ResponseEntity.ok(seriesService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seriesService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 