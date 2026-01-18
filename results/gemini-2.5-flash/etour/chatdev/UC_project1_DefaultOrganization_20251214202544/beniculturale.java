/*
This class represents a cultural object (Bene Culturale) with an ID, name, and description.
It serves as a data model for items displayed and managed in the system.
*/
public class BeniCulturale {
    private String id;
    private String name;
    private String description;
    /**
     * Constructs a new BeniCulturale object.
     *
     * @param id The unique identifier for the cultural object.
     * @param name The name of the cultural object.
     * @param description A brief description of the cultural object.
     */
    public BeniCulturale(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    /**
     * Returns the unique identifier of the cultural object.
     *
     * @return The ID of the cultural object.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the cultural object.
     *
     * @return The name of the cultural object.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the description of the cultural object.
     *
     * @return The description of the cultural object.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Provides a string representation of the BeniCulturale object,
     * primarily for display in UI components like JList.
     *
     * @return A formatted string containing the ID and name of the cultural object.
     */
    @Override
    public String toString() {
        return "ID: " + id + " - Name: " + name;
    }
    /**
     * Compares this BeniCulturale object to the specified object.
     * The result is true if and only if the argument is not null and is a BeniCulturale object
     * that has the same ID as this object.
     *
     * @param obj The object to compare this BeniCulturale against.
     * @return true if the given object represents a BeniCulturale equivalent to this object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BeniCulturale that = (BeniCulturale) obj;
        return id.equals(that.id);
    }
    /**
     * Returns a hash code value for the BeniCulturale object.
     * The hash code is based on the object's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}