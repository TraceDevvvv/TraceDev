package com.example.application.dto;

import com.example.core.domain.Tourist;

/**
 * Data Transfer Object for modifying tourist information.
 * Used to transfer data between presentation and application layers.
 */
public class ModifyTouristDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String nationality;

    public ModifyTouristDTO(Long id, String name, String email, String phoneNumber, String nationality) {
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
     * Converts this DTO to a Tourist entity.
     */
    public Tourist toTourist() {
        return new Tourist(id, name, email, phoneNumber, nationality);
    }

    /**
     * Creates a DTO from a Tourist entity.
     */
    public static ModifyTouristDTO fromTourist(Tourist tourist) {
        return new ModifyTouristDTO(
            tourist.getId(),
            tourist.getName(),
            tourist.getEmail(),
            tourist.getPhoneNumber(),
            tourist.getNationality()
        );
    }

    /**
     * Validates the DTO data.
     * In a real scenario, this might delegate to a validator.
     */
    public boolean validate() {
        return id != null && name != null && !name.isEmpty() && email != null && !email.isEmpty();
    }
}