/**
 * Represents a teaching entity with a unique name.
 * This class is immutable once created.
 */
public class Teaching {
    private final String name;

    /**
     * Constructs a new Teaching instance.
     *
     * @param name The name of the teaching. Must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public Teaching(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty.");
        }
        this.name = name.trim(); // Trim whitespace from the name
    }

    /**
     * Returns the name of the teaching.
     *
     * @return The name of the teaching.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this Teaching object to the specified object. The result is true if and only if
     * the argument is not null and is a Teaching object that represents the same sequence of
     * characters as this object.
     *
     * @param o The object to compare this Teaching against.
     * @return true if the given object represents a Teaching equivalent to this teaching, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return name.equals(teaching.name);
    }

    /**
     * Returns a hash code for this Teaching object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns a string representation of this Teaching object.
     *
     * @return A string representation of the teaching.
     */
    @Override
    public String toString() {
        return "Teaching{name='" + name + "'}";
    }
}