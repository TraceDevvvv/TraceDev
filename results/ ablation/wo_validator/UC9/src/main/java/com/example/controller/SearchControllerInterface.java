package com.example.controller;

import com.example.dto.PointOfRestDTO;

import java.util.List;
import java.util.Map;

/**
 * Interface for search controller.
 */
public interface SearchControllerInterface {
    List<PointOfRestDTO> handleSearchRequest(Map<String, Object> criteria);
}