package EntitySearch_1766409602;

/**
 * Represents an Address entity in the system.
 * Implements the Entity interface to allow it to be searched.
 */
public class Address implements Entity {
    private String id; // Unique identifier for the address, e.g., a building name or internal ID
    private String street;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Constructs a new Address instance.
     *
     * @param id A unique identifier for the address (e.g., "Main Campus", "Engineering Building").
     * @param street The street address.
     * @param city The city.
     * @param state The state.
     * @param zipCode The zip code.
     */
    public Address(String id, String street, String city, String state, String zipCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Returns the unique identifier of the address.
     *
     * @return The address ID.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns a string containing all searchable content for the address.
     * This includes its ID, street, city, state, and zip code.
     *
     * @return A concatenated string of searchable attributes.
     */
    @Override
    public String getSearchableContent() {
        // Convert to lowercase for case-insensitive searching
        return (id + " " + street + " " + city + " " + state + " " + zipCode).toLowerCase();
    }

    /**
     * Returns the display name of the address, which is its ID (e.g., "Main Campus").
     *
     * @return The descriptive name of the address.
     */
    @Override
    public String getDisplayName() {
        return id;
    }

    /**
     * Returns the type of the entity, which is "Address".
     *
     * @return The string "Address".
     */
    @Override
    public String getType() {
        return "Address";
    }

    /**
     * Provides a string representation of the Address object for debugging and display.
     *
     * @return A formatted string including the address type, ID, street, city, state, and zip code.
     */
    @Override
    public String toString() {
        return String.format("Type: %s, ID: %s, Street: %s, City: %s, State: %s, Zip: %s",
                             getType(), id, street, city, state, zipCode);
    }
}