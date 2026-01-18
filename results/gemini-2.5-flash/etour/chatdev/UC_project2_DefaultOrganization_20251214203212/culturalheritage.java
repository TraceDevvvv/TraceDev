/**
 * This class represents a Cultural Heritage object.
 * It's a simple Plain Old Java Object (POJO) that holds the data for a cultural good.
 * It includes an identifier (name in this case), description, and location.
 * The equals() and hashCode() methods are overridden to ensure proper uniqueness checks
 * when storing objects in collections (e.g., Set).
 */
import java.util.Objects;
public class CulturalHeritage {
    private String name;
    private String description;
    private String location;
    /**
     * Constructor for CulturalHeritage.
     *
     * @param name The unique name of the cultural heritage object.
     * @param description A detailed description of the object.
     * @param location The physical location where the object is found or stored.
     */
    public CulturalHeritage(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }
    /**
     * Returns the name of the cultural heritage.
     * @return The name as a String.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the description of the cultural heritage.
     * @return The description as a String.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the location of the cultural heritage.
     * @return The location as a String.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Overrides the equals method to define equality based on the 'name' attribute.
     * This is crucial for determining if two CulturalHeritage objects are considered duplicates.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal (have the same name), false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) o;
        // For uniqueness, we assume the 'name' is the primary identifier.
        return Objects.equals(name, that.name);
    }
    /**
     * Overrides the hashCode method to be consistent with the equals method.
     * If two objects are equal according to the equals() method, then their hashCode()
     * methods must return the same integer value.
     *
     * @return The hash code of the cultural heritage based on its name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    /**
     * Provides a string representation of the CulturalHeritage object.
     * @return A string containing the name, description, and location.
     */
    @Override
    public String toString() {
        return "CulturalHeritage{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
}