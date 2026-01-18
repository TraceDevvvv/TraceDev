package com.example.dto;

/**
 * Data transfer object for registration requests.
 */
public class RegistrationRequest {
    
    private String username;
    private String email;
    private String password;
    
    /**
     * Constructor for RegistrationRequest.
     * @param username the username
     * @param email the email
     * @param password the password
     */
    public RegistrationRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    // Getters as per class diagram
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    /**
     * Validates the registration request data.
     * @return true if data is valid
     */
    public boolean validate() {
        return username != null && !username.trim().isEmpty()
            && email != null && !email.trim().isEmpty()
            && password != null && !password.trim().isEmpty();
    }
    
    // Setters (not in class diagram but useful)
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}