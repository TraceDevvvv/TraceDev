package com.etour.tourist;

/**
 * Tourist class represents a tourist entity in the system.
 * It contains the tourist's personal information and account status.
 */
public class Tourist {
    private String id;          // Unique identifier for the tourist
    private String name;        // Full name of the tourist
    private String email;       // Email address of the tourist
    private String status;      // Account status (e.g., "ACTIVE", "INACTIVE", "DELETED")
    
    /**
     * Default constructor.
     */
    public Tourist() {
    }
    
    /**
     * Parameterized constructor to create a Tourist with all attributes.
     * 
     * @param id the unique identifier for the tourist
     * @param name the full name of the tourist
     * @param email the email address of the tourist
     * @param status the account status of the tourist
     */
    public Tourist(String id, String name, String email, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }
    
    /**
     * Gets the tourist's ID.
     * 
     * @return the tourist ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the tourist's ID.
     * 
     * @param id the tourist ID to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Gets the tourist's name.
     * 
     * @return the tourist name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the tourist's name.
     * 
     * @param name the tourist name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the tourist's email.
     * 
     * @return the tourist email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the tourist's email.
     * 
     * @param email the tourist email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the tourist's account status.
     * 
     * @return the account status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Sets the tourist's account status.
     * 
     * @param status the account status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Returns a string representation of the Tourist object.
     * Useful for logging and debugging.
     * 
     * @return string representation of the tourist
     */
    @Override
    public String toString() {
        return "Tourist [id=" + id + ", name=" + name + ", email=" + email + ", status=" + status + "]";
    }
}