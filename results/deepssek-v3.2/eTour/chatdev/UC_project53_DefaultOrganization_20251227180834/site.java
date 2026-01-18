import java.util.Objects;
/**
 * Represents a tourist attraction site with unique ID and name.
 * Implements equals and hashCode for proper collection handling.
 */
public class Site {
    private int id;
    private String name;
    public Site(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Site ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Site name cannot be null or empty");
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
            throw new IllegalArgumentException("Site name cannot be null or empty");
        }
        this.name = name.trim();
    }
    /**
     * Override equals to compare sites by ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return id == site.id;
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
        return String.format("Site{id=%d, name='%s'}", id, name);
    }
}