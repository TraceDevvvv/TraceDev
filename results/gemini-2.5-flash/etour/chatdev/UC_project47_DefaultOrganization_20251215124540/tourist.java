'''
Represents a Tourist's account data.
This is a simple Plain Old Java Object (POJO) to hold user information.
'''
public class Tourist {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    /**
     * Constructs a new Tourist object.
     *
     * @param id The unique identifier for the tourist.
     * @param username The tourist's username.
     * @param email The tourist's email address.
     * @param firstName The tourist's first name.
     * @param lastName The tourist's last name.
     */
    public Tourist(String id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * Gets the unique identifier of the tourist.
     *
     * @return The tourist's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the unique identifier of the tourist.
     *
     * @param id The new ID for the tourist.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the username of the tourist.
     *
     * @return The tourist's username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the tourist.
     *
     * @param username The new username for the tourist.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the email address of the tourist.
     *
     * @return The tourist's email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address of the tourist.
     *
     * @param email The new email for the tourist.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the first name of the tourist.
     *
     * @return The tourist's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the tourist.
     *
     * @param firstName The new first name for the tourist.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets the last name of the tourist.
     *
     * @return The tourist's last name.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name of the tourist.
     *
     * @param lastName The new last name for the tourist.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Provides a string representation of the Tourist object.
     *
     * @return A string containing tourist details.
     */
    @Override
    public String toString() {
        return "Tourist Profile:\n" +
               "  ID: " + id + "\n" +
               "  Username: " + username + "\n" +
               "  Email: " + email + "\n" +
               "  First Name: " + firstName + "\n" +
               "  Last Name: " + lastName;
    }
}