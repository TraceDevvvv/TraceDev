/*
 Data model class representing an address entry
 Contains all necessary fields for address information
 */
public class Address {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    public Address(String name, String street, String city, String state, String zipCode, String country) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
    // Getters for all fields
    public String getName() { return name; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }
    @Override
    public String toString() {
        return String.format("%s: %s, %s, %s %s, %s", 
            name, street, city, state, zipCode, country);
    }
}