package com.example;

import java.util.List;

/**
 * Controller for the address list use case.
 */
public class AddressListUseCaseController {
    private AddressRepository addressRepository;
    private AddressService addressService;
    private User currentUser;

    // Constructor
    public AddressListUseCaseController(AddressRepository addressRepository,
                                        AddressService addressService,
                                        User currentUser) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.currentUser = currentUser;
    }

    /**
     * Validates if the user has the required role to perform operations.
     */
    public boolean validateUserRole(User user) {
        // Assuming "administrator" role is required
        return "administrator".equals(user.getRole());
    }

    /**
     * Displays the list of addresses.
     * Orchestrates the flow as per sequence diagram.
     */
    public List<Address> displayAddressList() {
        // Check user role
        if (!validateUserRole(currentUser)) {
            throw new SecurityException("Access denied: User role invalid.");
        }

        // Use AddressService to get addresses (orchestration)
        List<Address> addresses = addressService.getAllAddresses();
        return addresses;
    }
}