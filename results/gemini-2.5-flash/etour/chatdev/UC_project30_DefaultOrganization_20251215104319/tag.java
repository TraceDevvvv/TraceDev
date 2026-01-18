/**
 * Represents a Tag entity in the system.
 * Includes basic attributes like name and description.
 * Overrides equals() and hashCode() for proper collection handling.
 */
import java.util.Objects;
import InvalidTagDataException; // Add this import
public class Tag {
    private String name;
    private String description;
    /**
     * Constructs a new Tag with the given name and description.
     * @param name The name of the tag. Cannot be null or empty.
     * @param description The description of the tag. Can be null or empty.
     * @throws InvalidTagDataException If the tag name is null or empty.
     */
    public Tag(String name, String description) throws InvalidTagDataException { // Modified to throw InvalidTagDataException
        // Basic validation for name, to ensure a Tag object is always created with a valid name.
        // This exception maps directly to the 'Errored' use case described in the requirements.
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidTagDataException("Tag name cannot be null or empty."); // Throw specific exception
        }
        this.name = name.trim();
        this.description = description != null ? description.trim() : "";
    }
    /**
     * Gets the name of the tag.
     * @return The tag's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the description of the tag.
     * @return The tag's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the tag.
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }
    /**
     * Overrides the equals method to compare tags based on their name.
     * Tag names are considered unique identifiers.
     * @param o The object to compare with.
     * @return true if the tags have the same name, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return name.equalsIgnoreCase(tag.name); // Case-insensitive comparison for tag names
    }
    /**
     * Overrides the hashCode method to generate a hash based on the tag's name.
     * This is consistent with the equals method.
     * @return The hash code of the tag.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase()); // Hash based on lowercase name for consistency with equals
    }
    /**
     * Returns a string representation of the Tag object.
     * @return A string containing the tag's name and description.
     */
    @Override
    public String toString() {
        return "Tag{name='" + name + "', description='" + description + "'}";
    }
}