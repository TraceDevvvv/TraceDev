'''
Represents the data model for a Refreshment Point.
Contains properties such as ID, name, address, contact phone, and description.
'''
package model;
import java.io.Serializable;
import java.util.Objects;
public class RefreshmentPoint implements Serializable {
    private String id;
    private String name;
    private String address;
    private String contactPhone;
    private String description;
    /**
     * Constructs a new RefreshmentPoint.
     *
     * @param id The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param address The physical address of the refreshment point.
     * @param contactPhone The contact phone number.
     * @param description A brief description of the refreshment point.
     */
    public RefreshmentPoint(String id, String name, String address, String contactPhone, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactPhone = contactPhone;
        this.description = description;
    }
    // Getters and Setters for all properties
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Provides a string representation of the RefreshmentPoint object.
     * @return A formatted string showing the point's details.
     */
    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", contactPhone='" + contactPhone + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
    /**
     * Checks if two RefreshmentPoint objects are equal based on their ID.
     * @param o The object to compare with.
     * @return True if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }
    /**
     * Generates a hash code for the RefreshmentPoint object based on its ID.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}