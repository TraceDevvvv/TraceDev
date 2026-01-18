/**
 * Represents a Tourist entity with personal details.
 * This class models the data structure for tourist information
 * that will be displayed in the tourist card view.
 * Follows proper encapsulation principles with getters and setters.
 */
public class Tourist {
    private String id;
    private String name;
    private String nationality;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private String dateOfBirth;
    /**
     * Constructs a new Tourist with all required details.
     * @param id Unique identifier for the tourist
     * @param name Full name of the tourist
     * @param nationality Nationality of the tourist
     * @param email Email address of the tourist
     * @param phoneNumber Contact phone number
     * @param passportNumber Passport identification number
     * @param dateOfBirth Date of birth in YYYY-MM-DD format
     */
    public Tourist(String id, String name, String nationality, String email,
                   String phoneNumber, String passportNumber, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
    }
    // Getters for all fields
    public String getId() { return id; }
    public String getName() { return name; }
    public String getNationality() { return nationality; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassportNumber() { return passportNumber; }
    public String getDateOfBirth() { return dateOfBirth; }
    // Setters for all fields to allow data modification
    /**
     * Sets the tourist's unique identifier
     * @param id New unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Sets the tourist's full name
     * @param name New full name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the tourist's nationality
     * @param nationality New nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    /**
     * Sets the tourist's email address
     * @param email New email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Sets the tourist's phone number
     * @param phoneNumber New phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Sets the tourist's passport number
     * @param passportNumber New passport number
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    /**
     * Sets the tourist's date of birth
     * @param dateOfBirth New date of birth in YYYY-MM-DD format
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**
     * Returns a detailed string representation of the tourist
     * @return Formatted string with tourist details
     */
    @Override
    public String toString() {
        return "Tourist [ID: " + id + 
               ", Name: " + name + 
               ", Nationality: " + nationality + 
               ", Email: " + email + 
               ", Phone: " + phoneNumber + 
               ", Passport: " + passportNumber + 
               ", DOB: " + dateOfBirth + "]";
    }
}