package hec.usecase;

/**
 * Request DTO for inserting a tag.
 * Contains the data needed to create a tag.
 */
public class InsertTagRequest {
    private final String name;
    private final String description;

    /**
     * Constructor.
     *
     * @param name        the tag name
     * @param description the tag description
     */
    public InsertTagRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the tag name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the tag description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Validates the request data.
     *
     * @return true if both name and description are non-empty, false otherwise
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
               description != null && !description.trim().isEmpty();
    }
}