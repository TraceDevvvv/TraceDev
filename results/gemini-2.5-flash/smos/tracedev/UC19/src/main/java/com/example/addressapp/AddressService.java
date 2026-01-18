package com.example.addressapp;

/**
 * Service layer for managing Address-related business logic.
 */
public class AddressService {
    private IAddressRepository addressRepository;

    /**
     * Constructs an AddressService with a given address repository.
     * @param addressRepository The repository to use for data access.
     */
    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Retrieves detailed information about an address.
     * Handles the transformation from Address entity to AddressDetailsDTO.
     * This method implements the data retrieval and transformation logic described in the sequence diagram.
     * @param addressId The ID of the address to retrieve.
     * @return An AddressDetailsDTO if the address is found, null if not found or an error occurred.
     */
    public AddressDetailsDTO getAddressDetails(String addressId) {
        System.out.println("AddressService: Request to get address details for ID: " + addressId);
        try {
            // Call the repository to find the address entity
            Address address = addressRepository.findById(addressId);

            if (address != null) {
                System.out.println("AddressService: Address entity retrieved, transforming to DTO.");
                // Transform the Address entity to AddressDetailsDTO
                return AddressDetailsDTO.fromAddress(address);
            } else {
                System.out.println("AddressService: Address with ID " + addressId + " not found or repository error.");
                return null; // Address not found or an error occurred during retrieval
            }
        } catch (Exception e) {
            // Catches any data access errors from the repository
            System.err.println("AddressService: Error retrieving address details: " + e.getMessage());
            return null; // Return null to indicate an error, as per sequence diagram
        }
    }
}