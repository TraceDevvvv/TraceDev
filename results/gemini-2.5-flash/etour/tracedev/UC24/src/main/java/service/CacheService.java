package com.example.service;

import com.example.model.Feedback;
import com.example.model.Site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Service for caching data to improve response time.
 * Added to satisfy requirement Quality Requirement: The system shall display feedback within a reasonable response time.
 */
public class CacheService {

    // A simple in-memory cache using HashMaps
    private final Map<String, Object> genericCache = new HashMap<>();
    private final Map<String, List<Site>> siteCache = new HashMap<>();
    private final Map<String, List<Feedback>> feedbackCache = new HashMap<>();

    // For demonstrating cache behavior:
    private static final long CACHE_EXPIRATION_MILLIS = TimeUnit.MINUTES.toMillis(5); // 5 minutes
    private final Map<String, Long> cacheTimestamps = new HashMap<>();


    /**
     * Retrieves an item from the generic cache by its key.
     * @param key The key of the item to retrieve.
     * @return The cached item, or null if not found or expired.
     */
    public Object get(String key) {
        if (isExpired(key)) {
            System.out.println("CacheService: Cache expired for key: " + key);
            genericCache.remove(key);
            cacheTimestamps.remove(key);
            return null;
        }
        return genericCache.get(key);
    }

    /**
     * Stores an item in the generic cache with a given key.
     * @param key The key to associate with the item.
     * @param value The item to cache.
     */
    public void put(String key, Object value) {
        genericCache.put(key, value);
        cacheTimestamps.put(key, System.currentTimeMillis());
        System.out.println("CacheService: Stored in cache: " + key);
    }

    /**
     * Retrieves the list of sites from the cache.
     * @return The cached list of sites, or null if not found or expired.
     */
    public List<Site> getCachedSites() {
        String key = "ALL_SITES";
        if (isExpired(key)) {
            System.out.println("CacheService: Site list cache expired.");
            siteCache.remove(key);
            cacheTimestamps.remove(key);
            return null;
        }
        System.out.println("CacheService: Retrieving sites from cache.");
        return siteCache.get(key);
    }

    /**
     * Stores the list of sites in the cache.
     * @param sites The list of sites to cache.
     */
    public void storeSites(List<Site> sites) {
        String key = "ALL_SITES";
        siteCache.put(key, sites);
        cacheTimestamps.put(key, System.currentTimeMillis());
        System.out.println("CacheService: Stored site list in cache.");
    }

    /**
     * Retrieves the list of feedback for a specific site from the cache.
     * @param siteId The ID of the site.
     * @return The cached list of feedback, or null if not found or expired.
     */
    public List<Feedback> getCachedFeedback(String siteId) {
        String key = "FEEDBACK_FOR_SITE_" + siteId;
        if (isExpired(key)) {
            System.out.println("CacheService: Feedback cache for site " + siteId + " expired.");
            feedbackCache.remove(key);
            cacheTimestamps.remove(key);
            return null;
        }
        System.out.println("CacheService: Retrieving feedback for site " + siteId + " from cache.");
        return feedbackCache.get(key);
    }

    /**
     * Stores the list of feedback for a specific site in the cache.
     * @param siteId The ID of the site.
     * @param feedback The list of feedback to cache.
     */
    public void storeFeedback(String siteId, List<Feedback> feedback) {
        String key = "FEEDBACK_FOR_SITE_" + siteId;
        feedbackCache.put(key, feedback);
        cacheTimestamps.put(key, System.currentTimeMillis());
        System.out.println("CacheService: Stored feedback for site " + siteId + " in cache.");
    }

    /**
     * Checks if a cached item for a given key has expired.
     * @param key The cache key.
     * @return true if expired or not in cache, false otherwise.
     */
    private boolean isExpired(String key) {
        Long timestamp = cacheTimestamps.get(key);
        return timestamp == null || (System.currentTimeMillis() - timestamp > CACHE_EXPIRATION_MILLIS);
    }

    /**
     * Clears all cache entries. Useful for testing.
     */
    public void clearCache() {
        genericCache.clear();
        siteCache.clear();
        feedbackCache.clear();
        cacheTimestamps.clear();
        System.out.println("CacheService: All caches cleared.");
    }
}