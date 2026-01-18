package com.etour.adapters;

import com.etour.entities.CulturalObject;
import java.util.List;

/**
 * Represents the result of a search operation.
 * Contains a list of cultural objects and the total count.
 */
public class SearchResult {
    private List<CulturalObject> culturalObjects;
    private int totalCount;

    public SearchResult() {
        this.culturalObjects = List.of();
        this.totalCount = 0;
    }

    // Getters
    public List<CulturalObject> getCulturalObjects() {
        return culturalObjects;
    }

    public int getTotalCount() {
        return totalCount;
    }

    // Setters
    public void setCulturalObjects(List<CulturalObject> culturalObjects) {
        this.culturalObjects = culturalObjects != null ? culturalObjects : List.of();
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "culturalObjects=" + culturalObjects.size() +
                ", totalCount=" + totalCount +
                '}';
    }
}