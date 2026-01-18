package com.system.service;

import com.system.domain.CulturalGood;
import java.util.Map;
import java.util.List;

/**
 * Interface for searching cultural goods (for traceability to prerequisite use case).
 */
public interface ISearchCulturalHeritage {
    List<CulturalGood> searchCulturalGoods(Map<String, Object> criteria);
}