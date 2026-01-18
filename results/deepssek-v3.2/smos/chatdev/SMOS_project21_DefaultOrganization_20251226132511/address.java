/**
 * Represents an address in the system.
 * Contains basic address information and associated teachings.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Address {
    private String id;
    private String street;
    private String city;
    private String zipCode;
    private List<Teaching> teachings;
    /**
     * Constructor to create an address with basic information.
     * 
     * @param id      Unique identifier for the address
     * @param street  Street name
     * @param city    City name
     * @param zipCode Postal code
     */
    public Address(String id, String street, String city, String zipCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.teachings = new ArrayList<>();
    }
    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public List<Teaching> getTeachings() {
        return new ArrayList<>(teachings); // Return a copy to preserve encapsulation
    }
    /**
     * Add a teaching to this address.
     * 
     * @param teaching Teaching to add
     * @return true if teaching was added successfully
     */
    public boolean addTeaching(Teaching teaching) {
        if (teaching != null && !teachings.contains(teaching)) {
            teachings.add(teaching);
            return true;
        }
        return false;
    }
    /**
     * Remove a teaching from this address.
     * 
     * @param teaching Teaching to remove
     * @return true if teaching was removed successfully
     */
    public boolean removeTeaching(Teaching teaching) {
        return teachings.remove(teaching);
    }
    /**
     * Get all teaching IDs associated with this address.
     * 
     * @return List of teaching IDs
     */
    public List<String> getTeachingIds() {
        List<String> ids = new ArrayList<>();
        for (Teaching t : teachings) {
            ids.add(t.getId());
        }
        return ids;
    }
    @Override
    public String toString() {
        return street + ", " + city + " " + zipCode;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}