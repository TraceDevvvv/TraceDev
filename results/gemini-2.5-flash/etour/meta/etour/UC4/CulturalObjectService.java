package com.example.culturalheritage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing and searching cultural objects.
 * It simulates a data store and provides methods to retrieve cultural objects
 * based on various search criteria.
 */
public class CulturalObjectService {

    // A simulated database of cultural objects
    private final List<CulturalObject> culturalObjects;

    /**
     * Constructs a CulturalObjectService and initializes it with some sample data.
     * In a real application, this would typically interact with a persistent data store.
     */
    public CulturalObjectService() {
        this.culturalObjects = new ArrayList<>();
        // Populate with sample data
        culturalObjects.add(new CulturalObject("CO001", "Mona Lisa", "A half-length portrait painting by Italian artist Leonardo da Vinci.", "Painting", "Louvre Museum, Paris", 1503));
        culturalObjects.add(new CulturalObject("CO002", "The Starry Night", "An oil on canvas by the Dutch Post-Impressionist painter Vincent van Gogh.", "Painting", "Museum of Modern Art, New York", 1889));
        culturalObjects.add(new CulturalObject("CO003", "Statue of David", "A masterpiece of Renaissance sculpture created in marble by Italian artist Michelangelo.", "Sculpture", "Galleria dell'Accademia, Florence", 1504));
        culturalObjects.add(new CulturalObject("CO004", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.", "Building", "Rome, Italy", 80));
        culturalObjects.add(new CulturalObject("CO005", "Terracotta Army", "A collection of terracotta sculptures depicting the armies of Qin Shi Huang, the first Emperor of China.", "Sculpture", "Xi'an, China", -210)); // -210 BC
        culturalObjects.add(new CulturalObject("CO006", "Great Pyramid of Giza", "The oldest and largest of the three pyramids in the Giza pyramid complex.", "Building", "Giza, Egypt", -2560)); // -2560 BC
        culturalObjects.add(new CulturalObject("CO007", "The Thinker", "A bronze sculpture by Auguste Rodin, usually placed on a stone pedestal.", "Sculpture", "Musée Rodin, Paris", 1904));
        culturalObjects.add(new CulturalObject("CO008", "Parthenon", "A former temple on the Athenian Acropolis, Greece, dedicated to the goddess Athena.", "Building", "Athens, Greece", -438)); // -438 BC
    }

    /**
     * Searches for cultural objects based on the provided search criteria.
     * This method filters the internal list of cultural objects according to
     * the specified keyword, category, location, and year range.
     *
     * @param criteria The SearchCriteria object containing the search parameters.
     * @return A list of CulturalObject that match the criteria. Returns an empty list if no matches are found.
     * @throws IllegalArgumentException if the yearFrom is greater than yearTo.
     */
    public List<CulturalObject> searchCulturalObjects(SearchCriteria criteria) {
        if (criteria == null) {
            // If no criteria are provided, return all objects (or an empty list, depending on requirements)
            // For this use case, we assume an empty criteria means no specific filter.
            return new ArrayList<>(culturalObjects);
        }

        // Validate year range
        if (criteria.getYearFrom() != null && criteria.getYearTo() != null && criteria.getYearFrom() > criteria.getYearTo()) {
            throw new IllegalArgumentException("Year 'from' cannot be greater than year 'to'.");
        }

        // Filter the cultural objects based on the criteria
        return culturalObjects.stream()
                .filter(obj -> matchesKeyword(obj, criteria.getKeyword()))
                .filter(obj -> matchesCategory(obj, criteria.getCategory()))
                .filter(obj -> matchesLocation(obj, criteria.getLocation()))
                .filter(obj -> matchesYearRange(obj, criteria.getYearFrom(), criteria.getYearTo()))
                .collect(Collectors.toList());
    }

    /**
     * Helper method to check if a cultural object's name or description contains the keyword.
     * Case-insensitive comparison.
     *
     * @param obj The CulturalObject to check.
     * @param keyword The keyword to search for.
     * @return true if the object matches the keyword, false otherwise.
     */
    private boolean matchesKeyword(CulturalObject obj, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return true; // No keyword specified, so it matches
        }
        String lowerCaseKeyword = keyword.toLowerCase();
        return (obj.getName() != null && obj.getName().toLowerCase().contains(lowerCaseKeyword)) ||
               (obj.getDescription() != null && obj.getDescription().toLowerCase().contains(lowerCaseKeyword));
    }

    /**
     * Helper method to check if a cultural object's category matches the specified category.
     * Case-insensitive comparison.
     *
     * @param obj The CulturalObject to check.
     * @param category The category to search for.
     * @return true if the object matches the category, false otherwise.
     */
    private boolean matchesCategory(CulturalObject obj, String category) {
        if (category == null || category.trim().isEmpty()) {
            return true; // No category specified, so it matches
        }
        return obj.getCategory() != null && obj.getCategory().equalsIgnoreCase(category);
    }

    /**
     * Helper method to check if a cultural object's location contains the specified location.
     * Case-insensitive comparison.
     *
     * @param obj The CulturalObject to check.
     * @param location The location to search for.
     * @return true if the object matches the location, false otherwise.
     */
    private boolean matchesLocation(CulturalObject obj, String location) {
        if (location == null || location.trim().isEmpty()) {
            return true; // No location specified, so it matches
        }
        return obj.getLocation() != null && obj.getLocation().toLowerCase().contains(location.toLowerCase());
    }

    /**
     * Helper method to check if a cultural object's year falls within the specified range.
     *
     * @param obj The CulturalObject to check.
     * @param yearFrom The start year of the range (inclusive). Can be null.
     * @param yearTo The end year of the range (inclusive). Can be null.
     * @return true if the object's year is within the range, false otherwise.
     */
    private boolean matchesYearRange(CulturalObject obj, Integer yearFrom, Integer yearTo) {
        boolean matchesFrom = (yearFrom == null) || (obj.getYear() >= yearFrom);
        boolean matchesTo = (yearTo == null) || (obj.getYear() <= yearTo);
        return matchesFrom && matchesTo;
    }
}