'''
Represents a historical convention or agreement.
This class serves as a data model for conventions, now including a reference to the PointOfRest it belongs to.
'''
package com.chatdev.viscon; // Using a package for better organization
import java.time.LocalDate;
import java.util.Objects;
/**
 * Represents a historical convention or agreement.
 * Contains details such as an ID, name, start date, end date, terms, and the ID of the PointOfRest it's associated with.
 */
public class Convention {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String terms;
    private String pointOfRestId; // New field to link convention to a PointOfRest
    /**
     * Constructs a new Convention object.
     *
     * @param id The unique identifier for the convention.
     * @param name A descriptive name for the convention.
     * @param startDate The start date of the convention.
     * @param endDate The end date of the convention.
     * @param terms A brief description of the convention's terms.
     * @param pointOfRestId The ID of the point of rest this convention belongs to.
     */
    public Convention(String id, String name, LocalDate startDate, LocalDate endDate, String terms, String pointOfRestId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.terms = terms;
        this.pointOfRestId = pointOfRestId; // Initialize the new field
    }
    // --- Getters for Convention properties ---
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public String getTerms() {
        return terms;
    }
    public String getPointOfRestId() { // New getter for pointOfRestId
        return pointOfRestId;
    }
    /**
     * Provides a string representation of the Convention object.
     *
     * @return A string containing the convention's ID and name.
     */
    @Override
    public String toString() {
        return "Convention{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", pointOfRestId='" + pointOfRestId + '\'' +
               '}';
    }
    /**
     * Compares this Convention object with another for equality.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convention that = (Convention) o;
        return Objects.equals(id, that.id);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code based on the convention's ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}