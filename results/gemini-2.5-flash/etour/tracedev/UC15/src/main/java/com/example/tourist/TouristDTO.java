package com.example.tourist;

/**
 * Data Transfer Object (DTO) for Tourist information.
 * Used for transferring data between layers (e.g., View and Service).
 */
public class TouristDTO {
    public String id;
    public String name;
    public String surname;
    public String email;
    public String address;

    // Default constructor
    public TouristDTO() {
    }

    // Constructor to create from a Tourist entity
    public TouristDTO(Tourist tourist) {
        if (tourist != null) {
            this.id = tourist.getId();
            this.name = tourist.getName();
            this.surname = tourist.getSurname();
            this.email = tourist.getEmail();
            this.address = tourist.getAddress();
        }
    }

    // Constructor to create with explicit values
    public TouristDTO(String id, String name, String surname, String email, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "TouristDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}