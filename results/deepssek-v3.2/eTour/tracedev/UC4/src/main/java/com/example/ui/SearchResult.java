package com.example.ui;

import com.example.domain.CulturalObject;
import java.util.List;

/**
 * Container for search results including timing information.
 * Implements quality requirement: Results within set time.
 */
public class SearchResult {
    private List<CulturalObject> objects;
    private long searchTime;
    private static final long MAX_SEARCH_TIME_MS = 5000;
    
    public SearchResult(List<CulturalObject> objects, long searchTime) {
        this.objects = objects;
        this.searchTime = searchTime;
    }
    
    public List<CulturalObject> getObjects() {
        return objects;
    }
    
    public long getSearchTime() {
        return searchTime;
    }
    
    public boolean isEmpty() {
        return objects == null || objects.isEmpty();
    }
    
    /**
     * Checks if search completed within time limit.
     * @return true if search time is within the 5000ms limit
     */
    public boolean isWithinTimeLimit() {
        return searchTime <= MAX_SEARCH_TIME_MS;
    }
}