package com.example.search.domain;

import java.util.Collections;
import java.util.List;

/**
 * DTO (Data Transfer Object) representing the results of a cultural object search.
 * It contains a list of CulturalObject instances.
 * Requirement R8: This DTO contains a list of CulturalObject.
 */
public class SearchResultDTO {
    private List<CulturalObject> culturalObjects;

    /**
     * Constructs a new SearchResultDTO.
     *
     * @param culturalObjects A list of CulturalObject instances that match the search criteria.
     */
    public SearchResultDTO(List<CulturalObject> culturalObjects) {
        // Ensure the list is immutable upon creation to prevent external modification.
        this.culturalObjects = culturalObjects != null ? Collections.unmodifiableList(culturalObjects) : Collections.emptyList();
    }

    /**
     * Returns the list of cultural objects found.
     *
     * @return An unmodifiable list of CulturalObject instances.
     */
    public List<CulturalObject> getCulturalObjects() {
        return culturalObjects;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
               "culturalObjects=" + culturalObjects +
               ", count=" + culturalObjects.size() +
               '}';
    }
}