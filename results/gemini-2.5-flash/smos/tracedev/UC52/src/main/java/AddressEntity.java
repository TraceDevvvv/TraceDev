// File: AddressEntity.java
/**
 * Represents an Address entity, extending AbstractEntity.
 * Includes specific attributes for street and city.
 */
public class AddressEntity extends AbstractEntity {
    // + street : String
    public String street;
    // + city : String
    public String city;

    /**
     * Constructs an AddressEntity with the specified details.
     *
     * @param id     The unique identifier for the address.
     * @param name   A descriptive name for the address (e.g., "Main Campus Building").
     * @param street The street address.
     * @param city   The city of the address.
     */
    public AddressEntity(String id, String name, String street, String city) {
        super(id, name);
        this.street = street;
        this.city = city;
    }

    /**
     * Returns the type of this entity, which is "Address".
     *
     * @return The string "Address".
     */
    @Override
    public String getType() {
        return "Address";
    }

    /**
     * Provides a string representation of the AddressEntity.
     *
     * @return A string combining the address's id, name, street, and city.
     */
    @Override
    public String toString() {
        return "AddressEntity{id='" + id + "', name='" + name + "', street='" + street + "', city='" + city + "'}";
    }
}