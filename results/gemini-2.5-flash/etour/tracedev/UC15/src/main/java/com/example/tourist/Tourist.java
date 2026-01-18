package com.example.tourist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a Tourist as a Domain Entity.
 */
public class Tourist {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String address;

    // Constructor
    public Tourist(String id, String name, String surname, String email, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Setters (if needed for ORM or specific use cases, but updateDetails is preferred for domain logic)
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Updates the details of the tourist. This is a domain-specific operation.
     *
     * @param name The new name.
     * @param surname The new surname.
     * @param email The new email.
     * @param address The new address.
     */
    public void updateDetails(String name, String surname, String email, String address) {
        System.out.println("[Tourist] Updating details for tourist ID: " + this.id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
    }

    /**
     * Validates the current state of the Tourist entity.
     *
     * @return A ValidationResult indicating if the entity is valid and any errors.
     */
    public ValidationResult validate() {
        System.out.println("[Tourist] Validating details for tourist ID: " + this.id);
        ValidationResult result = new ValidationResult();

        if (name == null || name.trim().isEmpty()) {
            result.addError("Name cannot be empty.");
        }
        if (surname == null || surname.trim().isEmpty()) {
            result.addError("Surname cannot be empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            result.addError("Email cannot be empty.");
        } else if (!isValidEmail(email)) {
            result.addError("Email format is invalid.");
        }
        if (address == null || address.trim().isEmpty()) {
            result.addError("Address cannot be empty.");
        }

        result.setValid(result.getErrors().isEmpty());
        return result;
    }

    private boolean isValidEmail(String email) {
        // Basic email validation regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}