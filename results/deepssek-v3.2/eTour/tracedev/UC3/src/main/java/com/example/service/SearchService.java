package com.example.service;

import com.example.dto.CulturalObjectDTO;
import java.util.Arrays;
import java.util.List;

/**
 * Service for searching cultural heritage.
 */
public class SearchService {
    public List<CulturalObjectDTO> getSearchResults() {
        // Simulated search results
        CulturalObjectDTO obj1 = new CulturalObjectDTO(1, "Object 1", "Desc 1", "Loc 1", "Period 1");
        CulturalObjectDTO obj2 = new CulturalObjectDTO(2, "Object 2", "Desc 2", "Loc 2", "Period 2");
        return Arrays.asList(obj1, obj2);
    }
}