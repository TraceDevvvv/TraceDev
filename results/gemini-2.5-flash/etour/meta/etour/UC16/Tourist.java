package com.etop.agency;

/**
 * Represents a tourist with an ID and name.
 * This class is a simple data structure to hold tourist information.
 */
public class Tourist {
    private String id;
    private String name;

    /**
     * Constructs a new Tourist object.
     *
     * @param id The unique identifier for the tourist.
     * @param name The name of the tourist.
     */
    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the tourist.
     *
     * @return The tourist's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the tourist.
     *
     * @param id The new ID for the tourist.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the tourist.
     *
     * @return The tourist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tourist.
     *
     * @param name The new name for the tourist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of the Tourist object.
     *
     * @return A string in the format "Tourist{id='...', name='...'}".
     */
    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }

    /**
     * Compares this Tourist object to the specified object. The result is true if and only if
     * the argument is not null and is a Tourist object that has the same ID as this object.
     *
     * @param o The object to compare this Tourist against.
     * @return true if the given object represents a Tourist equivalent to this tourist, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return id.equals(tourist.id);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}