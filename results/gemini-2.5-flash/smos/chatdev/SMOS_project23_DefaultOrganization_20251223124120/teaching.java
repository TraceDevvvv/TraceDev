/*
 * Represents a teaching entity with a name.
 */
public class Teaching {
    private String name;
    /**
     * Constructs a new Teaching object.
     * @param name The name of the teaching.
     */
    public Teaching(String name) {
        this.name = name;
    }
    /**
     * Gets the name of the teaching.
     * @return The name of the teaching.
     */
    public String getName() {
        return name;
    }
    /**
     * Static helper method to check the basic validity of a teaching name.
     * A name is considered valid if it's not null and not empty after trimming whitespace.
     * @param name The name to validate.
     * @return true if the name is valid, false otherwise.
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
    @Override
    public String toString() {
        return "Teaching [name=" + name + "]";
    }
}