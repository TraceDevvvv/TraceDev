'''
Represents an Address entity within the system.
This is a simple Plain Old Java Object (POJO) to hold address details.
'''
import java.io.Serializable;
public class Address implements Serializable {
    private static final long serialVersionUID = 1L; // Serial version UID for serialization
    private String street;
    private String city;
    private String state;
    private String zipCode;
    /**
     * Constructs a new Address object with the specified details.
     *
     * @param street The street name and number of the address.
     * @param city The city of the address.
     * @param state The state of the address.
     * @param zipCode The postal code (zip code) of the address.
     */
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    /**
     * Gets the street name and number.
     *
     * @return The street of the address.
     */
    public String getStreet() {
        return street;
    }
    /**
     * Sets the street name and number.
     *
     * @param street The new street for the address.
     */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * Gets the city of the address.
     *
     * @return The city of the address.
     */
    public String getCity() {
        return city;
    }
    /**
     * Sets the city of the address.
     *
     * @param city The new city for the address.
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Gets the state of the address.
     *
     * @return The state of the address.
     */
    public String getState() {
        return state;
    }
    /**
     * Sets the state of the address.
     *
     * @param state The new state for the address.
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * Gets the zip code of the address.
     *
     * @return The zip code of the address.
     */
    public String getZipCode() {
        return zipCode;
    }
    /**
     * Sets the zip code of the address.
     *
     * @param zipCode The new zip code for the address.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    /**
     * Returns a string representation of the Address object.
     *
     * @return A formatted string containing all address details.
     */
    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipCode;
    }
}