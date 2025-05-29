package com.gacha.controller;

import com.gacha.dto.BrandDTO;
import com.gacha.dto.BrandRequestDTO;
import com.gacha.dto.BrandResponseDTO;
import com.gacha.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> findAll() {
        return ResponseEntity.ok(brandService.findAllWithSeriesCount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findByIdWithSeriesCount(id));
    }

    @PostMapping
    public ResponseEntity<BrandDTO> create(@RequestBody BrandRequestDTO request) {
        return ResponseEntity.ok(brandService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @RequestBody BrandRequestDTO request) {
        return ResponseEntity.ok(brandService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 