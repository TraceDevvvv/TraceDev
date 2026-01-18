package com.example.datasource;

import java.util.Map;

/**
 * Data source interface for abstraction.
 * Allows different data sources (database, network, etc.)
 */
public interface IDataSource {
    Map<String, Object> fetchSiteData(String id);
}