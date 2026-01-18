/**
 * Represents an Address object with an ID, street, city, and a flag indicating if it has associated classes.
 */
public class Address {
    private String id;
    private String street;
    private String city;
    private boolean hasAssociatedClasses;
    /**
     * Constructs a new Address object.
     * @param id The unique identifier for the address.
     * @param street The street name of the address.
     * @param city The city of the address.
     * @param hasAssociatedClasses A boolean indicating if this address has associated classes.
     */
    public Address(String id, String street, String city, boolean hasAssociatedClasses) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.hasAssociatedClasses = hasAssociatedClasses;
    }
    /**
     * Gets the ID of the address.
     * @return The address ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the street name of the address.
     * @return The street name.
     */
    public String getStreet() {
        return street;
    }
    /**
     * Gets the city of the address.
     * @return The city.
     */
    public String getCity() {
        return city;
    }
    /**
     * Checks if the address has associated classes.
     * @return True if the address has associated classes, false otherwise.
     */
    public boolean hasAssociatedClasses() {
        return hasAssociatedClasses;
    }
    /**
     * Sets whether the address has associated classes.
     * @param hasAssociatedClasses True to indicate associated classes, false otherwise.
     */
    public void setHasAssociatedClasses(boolean hasAssociatedClasses) {
        this.hasAssociatedClasses = hasAssociatedClasses;
    }
    /**
     * Provides a string representation of the Address object, useful for displaying in UI components.
     * @return A formatted string showing the address details.
     */
    @Override
    public String toString() {
        return id + " - " + street + ", " + city;
    }
}