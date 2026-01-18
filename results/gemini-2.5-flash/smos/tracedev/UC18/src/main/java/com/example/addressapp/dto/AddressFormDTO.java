package com.example.addressapp.dto;

/**
 * Data Transfer Object (DTO) for Address form data.
 * This class encapsulates data submitted from a user interface form
 * for creating or updating an address.
 */
public class AddressFormDTO {
    public String addressName; // The name of the address from the form

    /**
     * Constructs an AddressFormDTO with the given address name.
     * @param name The name of the address as entered in the form.
     */
    public AddressFormDTO(String name) {
        this.addressName = name;
    }

    @Override
    public String toString() {
        return "AddressFormDTO{" +
               "addressName='" + addressName + '\'' +
               '}';
    }
}