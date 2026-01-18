
package com.example.agency;

/**
 * Represents an agency operator that interacts with the system.
 * Implements requirement REQ-004.
 */
public class AgencyOperator {
    
    private int id;
    private String name;
    private boolean sessionActive;
    
    /**
     * Constructs an AgencyOperator with the given ID and name.
     * @param id the operator ID
     * @param name the operator name
     */
    public AgencyOperator(int id, String name) {
        this.id = id;
        this.name = name;
        this.sessionActive = false;
    }
    
    /**
     * Returns the operator ID.
     * @return the ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the operator name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Checks if the session is active.
     * @return true if session is active
     */
    public boolean isSessionActive() {
        return sessionActive;
    }
    
    /**
     * Logs the operator into the system.
     */
    public void login() {
        this.sessionActive = true;
        System.out.println("Operator " + name + " logged in.");
    }
    
    /**
     * Logs the operator out of the system.
     */
    public void logout() {
        this.sessionActive = false;
        System.out.println("Operator " + name + " logged out.");
    }
    
    /**
     * Interacts with the ModifyBannerForm (requirement REQ-004).
     * @param form the form to interact with
     */
    public void interactWithForm(Object form) {
        // This method illustrates the interaction.
        System.out.println("AgencyOperator interacting with ModifyBannerForm");
    }
}
