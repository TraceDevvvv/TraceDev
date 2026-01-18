package com.example.cache;

import com.example.model.ConventionHistory;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple cache manager to store convention histories by point of rest ID.
 * Added to satisfy quality requirement (improve reliability).
 */
public class CacheManager {
    private Map<String, List<ConventionHistory>> cache;

    public CacheManager() {
        this.cache = new ConcurrentHashMap<>();
    }

    public List<ConventionHistory> getFromCache(String pointOfRestId) {
        return cache.get(pointOfRestId);
    }

    public void addToCache(String pointOfRestId, List<ConventionHistory> data) {
        cache.put(pointOfRestId, data);
    }

    public void clearCache() {
        cache.clear();
    }
}