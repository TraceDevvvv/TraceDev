package com.example.domain;

/**
 * Value Object for contact details.
 */
public class ContactDetails {
    private final String name;
    private final String email;
    private final String phone;

    public ContactDetails(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Simple validation for required fields.
     * @return true if name, email and phone are present.
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               phone != null && !phone.trim().isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}