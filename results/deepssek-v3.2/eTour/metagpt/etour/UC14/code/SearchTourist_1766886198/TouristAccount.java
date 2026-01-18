// TouristAccount.java
// This class represents a tourist account in the ETOUR system.
// It contains all the personal information and account details of a tourist.

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a tourist account in the system.
 * Contains personal information and account details.
 * Implements Serializable to allow object serialization if needed for persistence or network transmission.
 */
public class TouristAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Account information
    private String accountId;
    private String username;
    private String password; // In real system, this should be encrypted/hashed
    
    // Personal information
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationality;
    private LocalDate dateOfBirth;
    private String passportNumber;
    
    // Account status
    private boolean active;
    private LocalDate registrationDate;
    private LocalDate lastLoginDate;
    
    /**
     * Default constructor.
     */
    public TouristAccount() {
        this.registrationDate = LocalDate.now();
        this.active = true;
    }
    
    /**
     * Full constructor for creating a tourist account with all details.
     */
    public TouristAccount(String accountId, String username, String password, 
                         String firstName, String lastName, String email, 
                         String phoneNumber, String nationality, 
                         LocalDate dateOfBirth, String passportNumber) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.active = true;
        this.registrationDate = LocalDate.now();
    }
    
    // Getters and Setters
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getPassportNumber() {
        return passportNumber;
    }
    
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }
    
    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    
    /**
     * Marks the account as logged in by updating the last login date.
     */
    public void markAsLoggedIn() {
        this.lastLoginDate = LocalDate.now();
    }
    
    /**
     * Returns a string representation of the tourist account.
     * For security reasons, password is not included in the string representation.
     */
    @Override
    public String toString() {
        return "TouristAccount{" +
                "accountId='" + accountId + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportNumber='" + passportNumber + '\'' +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
    
    /**
     * Checks if this account matches the given account ID.
     * @param id The account ID to check
     * @return true if the account IDs match, false otherwise
     */
    public boolean matchesAccountId(String id) {
        return this.accountId != null && this.accountId.equalsIgnoreCase(id);
    }
    
    /**
     * Checks if this account matches the given username.
     * @param username The username to check
     * @return true if the usernames match, false otherwise
     */
    public boolean matchesUsername(String username) {
        return this.username != null && this.username.equalsIgnoreCase(username);
    }
    
    /**
     * Checks if this account matches the given email.
     * @param email The email to check
     * @return true if the emails match, false otherwise
     */
    public boolean matchesEmail(String email) {
        return this.email != null && this.email.equalsIgnoreCase(email);
    }
    
    /**
     * Checks if this account matches the given nationality.
     * @param nationality The nationality to check
     * @return true if the nationalities match, false otherwise
     */
    public boolean matchesNationality(String nationality) {
        return this.nationality != null && this.nationality.equalsIgnoreCase(nationality);
    }
    
    /**
     * Checks if this account matches the given full name (first and last name).
     * @param fullName The full name to check
     * @return true if the full name matches, false otherwise
     */
    public boolean matchesFullName(String fullName) {
        String accountFullName = getFullName().toLowerCase();
        return fullName != null && accountFullName.contains(fullName.toLowerCase());
    }
}