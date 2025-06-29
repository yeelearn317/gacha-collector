package com.gacha.controller;

import com.gacha.dto.BrandDTO;
import com.gacha.dto.BrandRequestDTO;
import com.gacha.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @Test
    void getAllBrands_ShouldReturnBrands() throws Exception {
        List<BrandDTO> brands = Arrays.asList(
            new BrandDTO(1L, "GSC", "Good Smile Company"),
            new BrandDTO(2L, "Taito", "Taito Corporation")
        );
        when(brandService.findAll()).thenReturn(brands);

        mockMvc.perform(get("/api/brands"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("GSC"))
                .andExpect(jsonPath("$[1].name").value("Taito"));
    }

    @Test
    void getBrand_ShouldReturnBrand() throws Exception {
        BrandDTO brand = new BrandDTO(1L, "GSC", "Good Smile Company");
        when(brandService.findById(1L)).thenReturn(Optional.of(brand));

        mockMvc.perform(get("/api/brands/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("GSC"));
    }

    @Test
    void createBrand_ShouldReturnCreatedBrand() throws Exception {
        BrandRequestDTO request = new BrandRequestDTO("GSC", "Good Smile Company");
        BrandDTO savedBrand = new BrandDTO(1L, "GSC", "Good Smile Company");
        when(brandService.save(any(BrandRequestDTO.class))).thenReturn(savedBrand);

        mockMvc.perform(post("/api/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"GSC\",\"description\":\"Good Smile Company\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("GSC"));
    }

    @Test
    void updateBrand_ShouldReturnUpdatedBrand() throws Exception {
        BrandRequestDTO request = new BrandRequestDTO("GSC Updated", "Updated Description");
        BrandDTO updatedBrand = new BrandDTO(1L, "GSC Updated", "Updated Description");
        when(brandService.update(eq(1L), any(BrandRequestDTO.class))).thenReturn(updatedBrand);

        mockMvc.perform(put("/api/brands/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"GSC Updated\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("GSC Updated"));
    }

    @Test
    void deleteBrand_ShouldReturnNoContent() throws Exception {
        doNothing().when(brandService).deleteById(1L);

        mockMvc.perform(delete("/api/brands/1"))
                .andExpect(status().isNoContent());

        verify(brandService, times(1)).deleteById(1L);
    }
} 