// SearchParameters.java
// This class represents the search criteria for tourist accounts in the ETOUR system.
// It contains various fields that can be used to filter tourist accounts.

/**
 * Represents search parameters for querying tourist accounts.
 * Allows searching by various criteria including account ID, username, name, email, nationality, etc.
 */
public class SearchParameters {
    private String accountId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String nationality;
    private String phoneNumber;
    private Boolean activeStatus;
    
    /**
     * Default constructor.
     */
    public SearchParameters() {
        // Initialize with default values (all null, meaning no filter applied)
    }
    
    /**
     * Constructor with common search parameters.
     */
    public SearchParameters(String accountId, String username, String firstName, 
                           String lastName, String email, String nationality) {
        this.accountId = accountId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationality = nationality;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Boolean getActiveStatus() {
        return activeStatus;
    }
    
    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    /**
     * Checks if the search parameters are empty (no criteria specified).
     * @return true if no search criteria are set, false otherwise
     */
    public boolean isEmpty() {
        return accountId == null && username == null && firstName == null && 
               lastName == null && email == null && nationality == null && 
               phoneNumber == null && activeStatus == null;
    }
    
    /**
     * Checks if a tourist account matches all the specified search criteria.
     * If a search parameter is null, that criterion is considered satisfied.
     * @param account The tourist account to check
     * @return true if the account matches all criteria, false otherwise
     */
    public boolean matches(TouristAccount account) {
        // Check account ID match (if specified)
        if (accountId != null && !accountId.trim().isEmpty()) {
            if (!account.matchesAccountId(accountId.trim())) {
                return false;
            }
        }
        
        // Check username match (if specified)
        if (username != null && !username.trim().isEmpty()) {
            if (!account.matchesUsername(username.trim())) {
                return false;
            }
        }
        
        // Check first name match (if specified)
        if (firstName != null && !firstName.trim().isEmpty()) {
            if (account.getFirstName() == null || 
                !account.getFirstName().toLowerCase().contains(firstName.trim().toLowerCase())) {
                return false;
            }
        }
        
        // Check last name match (if specified)
        if (lastName != null && !lastName.trim().isEmpty()) {
            if (account.getLastName() == null || 
                !account.getLastName().toLowerCase().contains(lastName.trim().toLowerCase())) {
                return false;
            }
        }
        
        // Check email match (if specified)
        if (email != null && !email.trim().isEmpty()) {
            if (!account.matchesEmail(email.trim())) {
                return false;
            }
        }
        
        // Check nationality match (if specified)
        if (nationality != null && !nationality.trim().isEmpty()) {
            if (!account.matchesNationality(nationality.trim())) {
                return false;
            }
        }
        
        // Check phone number match (if specified)
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            if (account.getPhoneNumber() == null || 
                !account.getPhoneNumber().toLowerCase().contains(phoneNumber.trim().toLowerCase())) {
                return false;
            }
        }
        
        // Check active status match (if specified)
        if (activeStatus != null) {
            if (account.isActive() != activeStatus) {
                return false;
            }
        }
        
        // All criteria matched (or were null)
        return true;
    }
    
    /**
     * Returns a string representation of the search parameters.
     */
    @Override
    public String toString() {
        return "SearchParameters{" +
                "accountId='" + accountId + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}