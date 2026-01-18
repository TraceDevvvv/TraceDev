/**
 * Represents an address with details such as name, city, country, and zip code.
 */
public class Address {
    private String name;
    private String city;
    private String country;
    private String zipCode;
    /**
     * Constructor to initialize an Address object.
     *
     * @param name    the name of the address (e.g., street name)
     * @param city    the city of the address
     * @param country the country of the address
     * @param zipCode the zip code of the address
     */
    public Address(String name, String city, String country, String zipCode) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
    /**
     * Get the address name.
     *
     * @return the address name
     */
    public String getName() {
        return name;
    }
    /**
     * Get the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * Get the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    /**
     * Get the zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }
}