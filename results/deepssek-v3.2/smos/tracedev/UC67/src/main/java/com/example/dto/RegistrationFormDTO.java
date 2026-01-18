package com.example.dto;

/**
 * Data Transfer Object for secure data transfer between layers
 * Note bottom: Data Transfer Object for secure data transfer between layers
 */
public class RegistrationFormDTO {
    private String name;
    private String surname;
    private String mobilePhone;
    private String email;
    private String username;
    private String password;
    private String confirmationPassword;

    // Default constructor
    public RegistrationFormDTO() {
    }

    // Constructor with all fields (assumed from sequence diagram)
    public RegistrationFormDTO(String name, String surname, String mobilePhone, 
                               String email, String username, String password, 
                               String confirmationPassword) {
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    /**
     * Validates the form data (basic validation)
     * As per class diagram method
     * @return true if validation passes
     */
    public boolean validate() {
        // Basic validation: check that required fields are not null or empty
        return name != null && !name.trim().isEmpty() &&
               surname != null && !surname.trim().isEmpty() &&
               mobilePhone != null && !mobilePhone.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty() &&
               confirmationPassword != null && !confirmationPassword.trim().isEmpty();
    }
}