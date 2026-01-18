package com.etour.controller;

import com.etour.dto.TouristDTO;
import com.etour.repository.TouristRepository;
import com.etour.mapper.TouristDataMapper;

import java.util.List;
import java.util.ArrayList;

/**
 * Controller for searching tourists.
 * Provides list of tourists as DTOs.
 */
public class SearchTouristController {
    private TouristRepository touristRepository;
    private TouristDataMapper dataMapper;

    public SearchTouristController(TouristRepository touristRepository, TouristDataMapper dataMapper) {
        this.touristRepository = touristRepository;
        this.dataMapper = dataMapper;
    }

    public List<TouristDTO> getTouristList() {
        // In a real scenario, this would fetch all tourists.
        // For simplicity, we return a hardcoded list.
        List<TouristDTO> list = new ArrayList<>();
        list.add(new TouristDTO("T001", "John Doe", "john@example.com", "1234567890"));
        list.add(new TouristDTO("T002", "Jane Smith", "jane@example.com", "0987654321"));
        return list;
    }
}