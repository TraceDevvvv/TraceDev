package com.example.core.domain;

/**
 * Represents a Tourist entity in the core domain.
 * Contains personal details and business logic for validation.
 */
public class Tourist {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String nationality;

    public Tourist(Long id, String name, String email, String phoneNumber, String nationality) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    /**
     * Updates the tourist's profile with new information.
     */
    public void updateProfile(String name, String email, String phoneNumber, String nationality) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
    }

    /**
     * Validates the tourist's data integrity.
     * In a real scenario, this would include business rules.
     */
    public boolean validate() {
        return id != null && name != null && !name.isEmpty() && email != null && !email.isEmpty();
    }
}