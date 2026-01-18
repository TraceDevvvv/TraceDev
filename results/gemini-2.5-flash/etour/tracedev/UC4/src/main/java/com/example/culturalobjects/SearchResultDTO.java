package com.example.culturalobjects;

import java.util.List;
import java.util.Collections; // For unmodifiable list

/**
 * Data Transfer Object (DTO): Encapsulates the results of a cultural object search.
 * This object is used to transfer formatted search results from the Service to the Controller and then to the View.
 */
public class SearchResultDTO {
    private final List<CulturalObject> culturalObjects;

    /**
     * Constructor for SearchResultDTO.
     * @param culturalObjects A list of cultural objects found during the search.
     */
    public SearchResultDTO(List<CulturalObject> culturalObjects) {
        // Create an unmodifiable copy to ensure data integrity once the DTO is created.
        this.culturalObjects = Collections.unmodifiableList(culturalObjects);
    }

    public List<CulturalObject> getCulturalObjects() {
        return culturalObjects;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
               "culturalObjects=" + culturalObjects +
               '}';
    }
}