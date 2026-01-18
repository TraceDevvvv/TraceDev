package hec.dto;

/**
 * Data Transfer Object for tag form.
 */
public class TagFormDTO {
    private String name;
    private String description;

    /**
     * Default constructor.
     */
    public TagFormDTO() {
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
     * Sets the tag name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the tag description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}