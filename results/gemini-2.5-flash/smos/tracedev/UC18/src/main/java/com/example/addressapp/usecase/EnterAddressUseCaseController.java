package com.example.addressapp.usecase;

import com.example.addressapp.dto.AddressFormDTO;
import com.example.addressapp.service.AddressService;

/**
 * Application layer controller for the "Enter Address" use case.
 * It coordinates between the presentation layer (AddressController) and the domain layer (AddressService).
 * It also handles data validation and error handling via ErrodatiUseCase.
 */
public class EnterAddressUseCaseController {
    private final AddressService addressService; // Dependency on AddressService
    private final ErrodatiUseCase errodatiUseCase; // Dependency on ErrodatiUseCase for error reporting

    /**
     * Constructs an EnterAddressUseCaseController with necessary dependencies.
     * @param service The AddressService to delegate address creation to.
     * @param errodati The ErrodatiUseCase to activate in case of validation errors.
     */
    public EnterAddressUseCaseController(AddressService service, ErrodatiUseCase errodati) {
        this.addressService = service;
        this.errodatiUseCase = errodati;
    }

    /**
     * Executes the "Enter Address" use case.
     * Validates the form data and, if valid, attempts to create an address.
     *
     * @param formData The data transferred from the presentation layer.
     * @return true if the address data was valid, false otherwise.
     *         Note: This method only indicates data validity. Successful persistence is handled by AddressService,
     *         and exceptions are propagated.
     */
    public boolean execute(AddressFormDTO formData) {
        System.out.println("[UseCase] Executing 'Enter Address' with data: " + formData.addressName);

        // Step 1: Validate address data
        if (!validateAddressData(formData)) {
            System.out.println("[UseCase] Address data is invalid.");
            // If invalid, activate Errodati and return false
            errodatiUseCase.activate("VALIDATION_ERROR", "Invalid address data provided for: " + formData.addressName);
            return false;
        }

        System.out.println("[UseCase] Address data is valid.");
        // The actual creation and persistence call via AddressService is handled by the AddressController
        // catching exceptions from AddressService. This method's return here only indicates validation status.
        return true; // Data is valid, AddressController will proceed to call AddressService
    }

    /**
     * Private helper method to validate the address form data.
     *
     * @param formData The AddressFormDTO containing the address details.
     * @return true if the data is considered valid, false otherwise.
     */
    private boolean validateAddressData(AddressFormDTO formData) {
        System.out.println("[UseCase] Validating address data for: " + formData.addressName);
        // Simple validation: address name must not be null or empty
        return formData.addressName != null && !formData.addressName.trim().isEmpty();
    }
}