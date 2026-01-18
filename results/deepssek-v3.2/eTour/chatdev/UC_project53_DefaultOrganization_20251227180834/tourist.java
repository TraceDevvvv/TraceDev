import java.util.Objects;
/**
 * Represents a tourist user with unique ID and name.
 * Implements proper validation and object comparison methods.
 */
public class Tourist {
    private int id;
    private String name;
    public Tourist(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Tourist ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist name cannot be null or empty");
        }
        this.id = id;
        this.name = name.trim();
    }
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    /**
     * Setter for name with validation
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist name cannot be null or empty");
        }
        this.name = name.trim();
    }
    /**
     * Override equals to compare tourists by ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return id == tourist.id;
    }
    /**
     * Override hashCode to use ID
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * String representation for debugging
     */
    @Override
    public String toString() {
        return String.format("Tourist{id=%d, name='%s'}", id, name);
    }
}