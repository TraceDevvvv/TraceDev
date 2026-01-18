package com.example.service;

import com.example.model.Address;
import com.example.model.AddressDto;
import com.example.model.ValidationResult;
import com.example.repository.AddressRepository;

/**
 * Service layer for address operations.
 */
public class AddressService {
    private AddressRepository addressRepository;

    // Constructor with dependency injection
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Validates address data.
     * Assumption: Validation rule - name must not be empty.
     */
    public ValidationResult validateAddressData(AddressDto addressData) {
        if (addressData.getName() == null || addressData.getName().trim().isEmpty()) {
            return new ValidationResult(false, "Address name is required");
        }
        return new ValidationResult(true);
    }

    /**
     * Inserts a new address after validation.
     */
    public Address insertAddress(AddressDto addressData) {
        Address address = new Address(addressData.getName());
        return addressRepository.save(address);
    }

    /**
     * Persisted Address - Sequence diagram return message
     */
    public Address persistedAddress(Address address) {
        // This method represents the return of a persisted address
        return address;
    }
}