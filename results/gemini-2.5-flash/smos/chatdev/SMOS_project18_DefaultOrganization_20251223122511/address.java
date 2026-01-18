/**
 * Represents a simple address entity with a name.
 */
public class Address {
    private String addressName;
    /**
     * Constructs a new Address object.
     * @param addressName The name of the address.
     */
    public Address(String addressName) {
        this.addressName = addressName;
    }
    /**
     * Gets the name of the address.
     * @return The address name.
     */
    public String getAddressName() {
        return addressName;
    }
    /**
     * Sets the name of the address.
     * @param addressName The new address name.
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    @Override
    public String toString() {
        return addressName;
    }
}