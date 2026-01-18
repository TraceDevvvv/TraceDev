package EntitySearch_1766409602;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Provides search functionality across all entities managed by the EntityManager.
 * This service takes search keywords and returns a filtered list of entities
 * that match the criteria. The search is case-insensitive and considers
 * all searchable content provided by the Entity interface.
 */
public class SearchService {
    private final EntityManager entityManager;

    /**
     * Constructs a new SearchService with a reference to the EntityManager.
     *
     * @param entityManager The EntityManager instance containing all searchable entities.
     *                      Must not be null.
     * @throws NullPointerException if the provided entityManager is null.
     */
    public SearchService(EntityManager entityManager) {
        this.entityManager = Objects.requireNonNull(entityManager, "EntityManager cannot be null.");
    }

    /**
     * Searches for entities that contain all the provided keywords in their
     * searchable content. The search is case-insensitive.
     *
     * @param keywords A space-separated string of keywords to search for.
     *                 If the string is null or empty, an empty list is returned.
     * @return A list of Entity objects that match all keywords.
     *         Returns an empty list if no matches are found or if keywords are empty.
     */
    public List<Entity> searchEntities(String keywords) {
        if (keywords == null || keywords.trim().isEmpty()) {
            return Collections.emptyList(); // No keywords, no results.
        }

        // Normalize keywords for case-insensitive matching
        String[] keywordArray = keywords.toLowerCase(Locale.ROOT).split("\\s+");
        List<Entity> matchingEntities = new ArrayList<>();

        // Iterate through all entities managed by the EntityManager
        for (Entity entity : entityManager.getAllEntities()) {
            // Get the searchable content of the entity and normalize it
            String entityContent = entity.getSearchableContent().toLowerCase(Locale.ROOT);

            boolean allKeywordsMatch = true;
            // Check if all keywords are present in the entity's searchable content
            for (String keyword : keywordArray) {
                if (!entityContent.contains(keyword)) {
                    allKeywordsMatch = false;
                    break; // One keyword not found, no need to check further for this entity
                }
            }

            if (allKeywordsMatch) {
                matchingEntities.add(entity);
            }
        }

        return matchingEntities;
    }
}