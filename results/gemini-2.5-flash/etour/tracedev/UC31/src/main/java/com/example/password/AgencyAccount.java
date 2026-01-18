package com.example.password;

/**
 * Represents an agency account with an ID and a hashed password.
 */
public class AgencyAccount {
    private String accountId;
    private String hashedPassword;

    /**
     * Constructs a new AgencyAccount.
     * @param accountId The unique identifier for the account.
     * @param hashedPassword The initial hashed password for the account.
     */
    public AgencyAccount(String accountId, String hashedPassword) {
        this.accountId = accountId;
        this.hashedPassword = hashedPassword;
    }

    /**
     * Gets the account ID.
     * @return The account ID.
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the hashed password for the account.
     * @param hashedPassword The new hashed password.
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        System.out.println("DEBUG: Account '" + accountId + "' hashed password updated internally.");
    }

    /**
     * Gets the hashed password.
     * @return The hashed password.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public String toString() {
        return "AgencyAccount{" +
               "accountId='" + accountId + '\'' +
               ", hashedPassword='" + hashedPassword + '\'' +
               '}';
    }
}