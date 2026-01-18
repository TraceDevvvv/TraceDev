package com.example.culturalheritage;

/**
 * Represents a cultural heritage object with an ID and name.
 * This class is a simple data structure to hold information about a cultural heritage.
 */
public class CulturalHeritage {
    private String id;
    private String name;

    /**
     * Constructs a new CulturalHeritage object.
     *
     * @param id   The unique identifier of the cultural heritage.
     * @param name The name of the cultural heritage.
     */
    public CulturalHeritage(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the cultural heritage.
     *
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the cultural heritage.
     *
     * @param id The new ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the cultural heritage.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cultural heritage.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of the CulturalHeritage object.
     *
     * @return A string in the format "ID: [id], Name: [name]".
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }

    /**
     * Compares this CulturalHeritage object to the specified object.
     * The result is true if and only if the argument is not null and is a CulturalHeritage object
     * that has the same ID as this object.
     *
     * @param obj The object to compare this CulturalHeritage against.
     * @return true if the given object represents a CulturalHeritage equivalent to this cultural heritage, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) obj;
        return id.equals(that.id);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}