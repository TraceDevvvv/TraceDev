package com.example.controller;

import com.example.model.Address;
import com.example.model.AddressDto;
import com.example.model.Result;
import com.example.model.ValidationResult;
import com.example.service.AddressService;
import com.example.view.AddressListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for address-related operations.
 */
public class AddressController {
    private AddressService addressService;

    // Constructor with dependency injection
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Returns a list of addresses for display.
     * Assumption: Currently returns an empty list for simplicity.
     */
    public AddressListView showAddressList() {
        List<Address> addresses = new ArrayList<>(); // In real scenario, fetch from service
        return new AddressListView(addresses);
    }

    /**
     * Shows the new address form (no-op in controller, handled by UI).
     */
    public void showNewAddressForm() {
        // Form display is handled by UI
    }

    /**
     * Returns the address form as per sequence diagram.
     */
    public AddressListView returnAddressForm() {
        // This method returns the form data needed by UI
        // In this implementation, we return an empty list view
        return new AddressListView(new ArrayList<>());
    }

    /**
     * Creates a new address as per sequence diagram.
     */
    public Result createAddress(AddressDto addressDto) {
        // Step 1: Validate address data
        ValidationResult validationResult = addressService.validateAddressData(addressDto);
        
        if (!validationResult.isValid()) {
            // Data invalid: return error result
            return new Result(false, validationResult.getErrorMessage());
        }

        // Step 2: Data is valid, attempt to insert
        try {
            Address address = addressService.insertAddress(addressDto);
            // Success: return success result with address
            return new Result(true, "Address created", address);
        } catch (Exception e) {
            // SMOS connection failed or other error
            return new Result(false, "SMOS server connection failed");
        }
    }

    /**
     * Cancels address creation (simulates session cleanup).
     */
    public Result cancelAddressCreation(String sessionId) {
        // In a real system, would clean up session resources
        return new Result(false, "Operation cancelled by administrator");
    }

    /**
     * Handles cancel request from UI.
     */
    public Result cancelRequest(String sessionId) {
        return cancelAddressCreation(sessionId);
    }
}