'''
Represents a single tourist account in the system.
This class serves as a data model for tourist information.
'''
class Tourist {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    /**
     * Constructor to create a new Tourist object.
     * @param id Unique identifier for the tourist.
     * @param firstName The first name of the tourist.
     * @param lastName The last name of the tourist.
     * @param email The email address of the tourist.
     * @param country The country of residence of the tourist.
     */
    public Tourist(String id, String firstName, String lastName, String email, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
    }
    // --- Getters ---
    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getCountry() {
        return country;
    }
    // --- Setters (optional, included for completeness if mutation is needed) ---
    public void setId(String id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}