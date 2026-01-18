/**
 * Represents a 'Turista' (Tourist) entity with basic personal details.
 * This class acts as a data model for tourist information within the system.
 */
public class Tourist {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    /**
     * Constructs a new Tourist object.
     * @param id Unique identifier for the tourist.
     * @param firstName The first name of the tourist.
     * @param lastName The last name of the tourist.
     * @param email The email address of the tourist.
     * @param phoneNumber The phone number of the tourist.
     * @param address The physical address of the tourist.
     */
    public Tourist(String id, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    /**
     * Gets the unique identifier of the tourist.
     * @return The tourist's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the first name of the tourist.
     * @return The tourist's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Gets the last name of the tourist.
     * @return The tourist's last name.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Gets the email address of the tourist.
     * @return The tourist's email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the phone number of the tourist.
     * @return The tourist's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Gets the address of the tourist.
     * @return The tourist's address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Returns a string representation of the Tourist object, primarily used for display in lists.
     * @return A string combining first name and last name.
     */
    @Override
    public String toString() {
        // Handle cases where ID might be empty (e.g., loading messages)
        if (id == null || id.isEmpty()) {
            return firstName + " " + lastName;
        }
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}