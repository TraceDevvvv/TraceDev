package com.example.touristapp.domain;

/**
 * Represents a Tourist Account in the domain layer.
 * This class holds the core business logic and state for a tourist account.
 */
public class TouristAccount {
    private String id;
    private String name;
    private AccountStatus status;

    /**
     * Constructs a new TouristAccount.
     * @param id The unique identifier for the account.
     * @param name The name associated with the account.
     * @param status The initial status of the account.
     */
    public TouristAccount(String id, String name, AccountStatus status) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Account name cannot be null or empty.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Account status cannot be null.");
        }
        this.id = id;
        this.name = name;
        this.status = status;
    }

    /**
     * Enables the tourist account by setting its status to ENABLED.
     */
    public void enable() {
        if (this.status == AccountStatus.ENABLED) {
            System.out.println("TouristAccount " + id + " is already enabled.");
            return;
        }
        this.status = AccountStatus.ENABLED;
        System.out.println("TouristAccount " + id + " enabled.");
    }

    /**
     * Disables the tourist account by setting its status to DISABLED.
     */
    public void disable() {
        if (this.status == AccountStatus.DISABLED) {
            System.out.println("TouristAccount " + id + " is already disabled.");
            return;
        }
        this.status = AccountStatus.DISABLED;
        System.out.println("TouristAccount " + id + " disabled.");
    }

    /**
     * Checks if the tourist account is currently enabled.
     * @return true if the account status is ENABLED, false otherwise.
     */
    public boolean isEnabled() {
        return this.status == AccountStatus.ENABLED;
    }

    /**
     * Gets the current status of the tourist account.
     * @return The AccountStatus of the account.
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * Gets the unique identifier of the tourist account.
     * @return The account ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name associated with the tourist account.
     * @return The account name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TouristAccount{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", status=" + status +
               '}';
    }
}