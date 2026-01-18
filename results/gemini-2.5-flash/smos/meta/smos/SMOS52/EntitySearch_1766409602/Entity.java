package EntitySearch_1766409602;

/**
 * Represents a generic searchable entity within the system.
 * All concrete entity types (e.g., Course, Teaching, Address, User)
 * must implement this interface to be discoverable by the search service.
 */
public interface Entity {

    /**
     * Returns a unique identifier for this entity.
     * This ID can be used to distinguish between different entities of the same or different types.
     *
     * @return A unique string identifier for the entity.
     */
    String getId();

    /**
     * Returns a string representation of the entity's content that should be
     * considered for keyword-based searching. This typically includes names,
     * descriptions, or other relevant textual attributes.
     * The implementation should concatenate all searchable fields into a single string.
     *
     * @return A string containing all searchable text content of the entity.
     */
    String getSearchableContent();

    /**
     * Returns a user-friendly display name or title for the entity.
     * This is used when presenting search results to the administrator.
     *
     * @return A descriptive name for the entity.
     */
    String getDisplayName();

    /**
     * Returns the type of the entity as a string (e.g., "Course", "User").
     * This helps in categorizing and displaying search results.
     *
     * @return The type of the entity.
     */
    String getType();
}