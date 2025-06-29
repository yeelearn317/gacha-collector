package com.gacha.controller;

import com.gacha.dto.BrandDTO;
import com.gacha.dto.BrandRequestDTO;
import com.gacha.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "品牌管理", description = "品牌的 CRUD 操作")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @Operation(summary = "取得所有品牌")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "取得單一品牌")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable Long id) {
        return brandService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "新增品牌")
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandService.save(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新品牌")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandRequestDTO request) {
        try {
            return ResponseEntity.ok(brandService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除品牌")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 