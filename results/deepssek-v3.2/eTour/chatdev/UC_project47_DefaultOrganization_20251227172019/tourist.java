/**
 * Represents a tourist user in the system.
 * Contains personal information and validation logic.
 */
import java.util.regex.Pattern;
public class Tourist {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    // Phone validation pattern (basic international format)
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[+]?[0-9\\s-]{10,}$");
    public Tourist(String username, String firstName, String lastName, 
                   String email, String phoneNumber, String address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    // Getters
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    /**
     * Validates all fields according to business rules.
     * @return true if all fields are valid, false otherwise
     */
    public boolean validateFields() {
        // Check for empty required fields
        if (firstName == null || firstName.trim().isEmpty() ||
            lastName == null || lastName.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        // Validate email format
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }
        // Validate phone number format
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            return false;
        }
        // Address is optional - no validation needed
        return true;
    }
    @Override
    public String toString() {
        return String.format("Tourist[username=%s, name=%s %s, email=%s]", 
            username, firstName, lastName, email);
    }
}