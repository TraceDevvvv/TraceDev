package com.example.repository;

import com.example.dto.SiteDTO;
import com.example.datasource.IDataSource;

import java.util.Map;

/**
 * Concrete repository implementation.
 * Fetches site data from a data source.
 */
public class SiteRepository implements ISiteRepository {
    private final IDataSource dataSource;

    // Constructor with dependency injection
    public SiteRepository(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SiteDTO getSiteById(String id) {
        // Fetch raw data from data source
        Map<String, Object> rawData = dataSource.fetchSiteData(id);
        
        // If no data is found, return null (handles connection interruption case)
        if (rawData == null || rawData.isEmpty()) {
            return null;
        }
        
        // Extract values with default fallbacks
        String name = (String) rawData.getOrDefault("name", "");
        String desc = (String) rawData.getOrDefault("description", "");
        String location = (String) rawData.getOrDefault("location", "");
        double rating = ((Number) rawData.getOrDefault("rating", 0.0)).doubleValue();
        
        // Return a DTO
        return new SiteDTO(id, name, desc, location, rating);
    }
}