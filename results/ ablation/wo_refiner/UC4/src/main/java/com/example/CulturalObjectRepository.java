package com.example;

import java.util.List;

// Repository interface for cultural objects
public interface CulturalObjectRepository {
    // Finds all cultural objects matching the search criteria
    List<CulturalObject> findAll(SearchCriteriaDTO criteria);
}