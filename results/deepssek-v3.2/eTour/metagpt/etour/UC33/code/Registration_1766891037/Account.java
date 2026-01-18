import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Account class representing a registered user account in the system.
 * An Account is created after successful registration and contains
 * additional account-specific attributes beyond basic user information.
 */
public class Account {
    private String accountId;
    private User user;
    private LocalDateTime accountCreationDate;
    private LocalDateTime lastLoginDate;
    private boolean isActive;
    private String accountStatus;
    
    /**
     * Constructor for creating a new Account from a User object.
     * This is typically called after successful registration.
     * 
     * @param user the User object containing user details
     */
    public Account(User user) {
        this.accountId = UUID.randomUUID().toString();
        this.user = user;
        this.user.setGuest(false); // User is no longer a guest after account creation
        this.accountCreationDate = LocalDateTime.now();
        this.lastLoginDate = null; // No login yet
        this.isActive = true;
        this.accountStatus = "ACTIVE";
    }
    
    /**
     * Get the unique account ID.
     * 
     * @return the account ID
     */
    public String getAccountId() {
        return accountId;
    }
    
    /**
     * Get the associated User object.
     * 
     * @return the User object
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Set the associated User object.
     * 
     * @param user the User object to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Get the account creation date and time.
     * 
     * @return the creation date
     */
    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }
    
    /**
     * Get the last login date and time.
     * 
     * @return the last login date, or null if never logged in
     */
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }
    
    /**
     * Update the last login date to the current date and time.
     * This method should be called when the user successfully logs in.
     */
    public void updateLastLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }
    
    /**
     * Check if the account is active.
     * 
     * @return true if the account is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * Set the account active status.
     * 
     * @param active true to activate the account, false to deactivate
     */
    public void setActive(boolean active) {
        isActive = active;
        this.accountStatus = active ? "ACTIVE" : "INACTIVE";
    }
    
    /**
     * Get the account status as a string.
     * 
     * @return the account status
     */
    public String getAccountStatus() {
        return accountStatus;
    }
    
    /**
     * Set the account status.
     * 
     * @param accountStatus the status to set (e.g., "ACTIVE", "INACTIVE", "SUSPENDED")
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    /**
     * Convert the account to a string representation.
     * Includes user details and account-specific information.
     * 
     * @return string representation of the account
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", user=" + user +
                ", accountCreationDate=" + accountCreationDate +
                ", lastLoginDate=" + lastLoginDate +
                ", isActive=" + isActive +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
    
    /**
     * Validate the account data for completeness.
     * This method checks if all required account fields are present.
     * 
     * @return true if the account data is valid, false otherwise
     */
    public boolean validate() {
        if (accountId == null || accountId.isEmpty()) {
            return false;
        }
        if (user == null) {
            return false;
        }
        if (accountCreationDate == null) {
            return false;
        }
        if (accountStatus == null || accountStatus.isEmpty()) {
            return false;
        }
        return true;
    }
}