```java
package DeleteTag_1765892478;

/**
 * Represents a search tag with a unique identifier and a name.
 * This class is immutable to ensure data integrity once a tag is created.
 */
public class Tag {
    private final String id;
    private final String name;

    /**
     * Constructs a new Tag instance.
     *
     * @param id   The unique identifier for the tag. Must not be null or empty.
     * @param name The name of the tag. Must not be null or empty.
     * @throws IllegalArgumentException if id or name is null or empty.
     */
    public Tag(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag ID cannot be null or empty.");
        }
        if (name == null || name.trim