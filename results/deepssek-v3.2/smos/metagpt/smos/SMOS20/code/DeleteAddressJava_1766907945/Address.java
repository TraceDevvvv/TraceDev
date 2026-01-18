import java.util.ArrayList;
import java.util.List;

/**
 * Address class representing an address entity in the system.
 * Each address has an ID, details, and may have associated classes.
 */
public class Address {
    private int id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private List<String> associatedClasses; // List of class names associated with this address

    /**
     * Constructor to create a new Address.
     * 
     * @param id Unique identifier for the address
     * @param street Street name
     * @param city City name
     * @param zipCode Postal code
     * @param country Country name
     */
    public Address(int id, String street, String city, String zipCode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.associatedClasses = new ArrayList<>();
    }

    /**
     * Get the address ID.
     * 
     * @return Address ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the street name.
     * 
     * @return Street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Get the city name.
     * 
     * @return City name
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the zip code.
     * 
     * @return Zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Get the country name.
     * 
     * @return Country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get the list of associated classes.
     * 
     * @return List of associated class names
     */
    public List<String> getAssociatedClasses() {
        return new ArrayList<>(associatedClasses); // Return a copy to maintain encapsulation
    }

    /**
     * Add an associated class to this address.
     * 
     * @param className Name of the class to associate
     */
    public void addAssociatedClass(String className) {
        if (className != null && !className.trim().isEmpty()) {
            associatedClasses.add(className);
        }
    }

    /**
     * Remove an associated class from this address.
     * 
     * @param className Name of the class to remove
     * @return true if the class was removed, false otherwise
     */
    public boolean removeAssociatedClass(String className) {
        return associatedClasses.remove(className);
    }

    /**
     * Check if this address has any associated classes.
     * 
     * @return true if there are associated classes, false otherwise
     */
    public boolean hasAssociatedClasses() {
        return !associatedClasses.isEmpty();
    }

    /**
     * Get the full address as a formatted string.
     * 
     * @return Formatted address string
     */
    public String getFullAddress() {
        return String.format("%s, %s, %s %s", street, city, zipCode, country);
    }

    /**
     * Get a string representation of the address.
     * 
     * @return String representation including ID and full address
     */
    @Override
    public String toString() {
        return String.format("Address [ID: %d, %s]", id, getFullAddress());
    }
}