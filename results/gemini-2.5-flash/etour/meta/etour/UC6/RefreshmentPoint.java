import java.util.Objects;

/**
 * Represents a refreshment point in the system.
 * This class holds basic information about a refreshment point, such as its ID, name, and location.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;

    /**
     * Constructs a new RefreshmentPoint.
     *
     * @param id The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param location The geographical location or description of the refreshment point.
     */
    public RefreshmentPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    /**
     * Returns the unique identifier of the refreshment point.
     *
     * @return The ID of the refreshment point.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the refreshment point.
     *
     * @param id The new ID for the refreshment point.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the refreshment point.
     *
     * @return The name of the refreshment point.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the refreshment point.
     *
     * @param name The new name for the refreshment point.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the location of the refreshment point.
     *
     * @return The location of the refreshment point.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the refreshment point.
     *
     * @param location The new location for the refreshment point.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns a string representation of the RefreshmentPoint object.
     *
     * @return A string containing the ID, name, and location of the refreshment point.
     */
    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", location='" + location + '\'' +
               '}';
    }

    /**
     * Compares this RefreshmentPoint to the specified object. The result is true if and only if
     * the argument is not null and is a RefreshmentPoint object that has the same ID as this object.
     *
     * @param o The object to compare this RefreshmentPoint against.
     * @return true if the given object represents a RefreshmentPoint equivalent to this refreshment point, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}