package com.example.addressapp;

import com.example.addressapp.dto.AddressFormDTO;
import com.example.addressapp.infrastructure.AddressDatabaseRepository;
import com.example.addressapp.infrastructure.AddressRepository;
import com.example.addressapp.presentation.AddressController;
import com.example.addressapp.presentation.AddressView;
import com.example.addressapp.service.AddressService;
import com.example.addressapp.usecase.AuthenticationService;
import com.example.addressapp.usecase.EnterAddressUseCaseController;
import com.example.addressapp.usecase.ErrodatiUseCase;
import com.example.addressapp.usecase.ViewingAddressesUseCaseController;

import java.util.Scanner;

/**
 * Main entry point for the Address Application.
 * This class is responsible for setting up the application context (dependency injection)
 * and demonstrating the use case flow described in the sequence diagram.
 */
public class MainApplication {

    public static void main(String[] args) {
        // --- Application Setup: Dependency Injection ---
        // Infrastructure Layer
        AddressRepository addressRepository = new AddressDatabaseRepository();

        // Application/Domain Layer
        AddressService addressService = new AddressService(addressRepository);
        ErrodatiUseCase errodatiUseCase = new ErrodatiUseCase();
        EnterAddressUseCaseController enterAddressUseCaseController =
                new EnterAddressUseCaseController(addressService, errodatiUseCase);
        AuthenticationService authenticationService = new AuthenticationService(); // EC1
        ViewingAddressesUseCaseController viewingAddressesUseCaseController = new ViewingAddressesUseCaseController(); // EC2

        // Presentation Layer
        AddressView addressView = new AddressView();
        AddressController addressController =
                new AddressController(enterAddressUseCaseController, addressView, addressService,
                                      authenticationService, viewingAddressesUseCaseController);

        // --- Scenario Simulation ---
        System.out.println("--- Starting Address Application Simulation ---");
        Scanner scanner = new Scanner(System.in);

        // Scenario 1: Successful Address Entry
        System.out.println("\n--- Scenario 1: Successful Address Entry ---");
        // These checks are implicitly handled by onNewAddressButtonClick in the controller for this setup,
        // but the sequence diagram shows Admin (client) doing them first.
        // For strict sequence diagram adherence where Admin orchestrates:
        System.out.println("[Admin] Checking login status...");
        if (!authenticationService.isLoggedIn()) {
            System.out.println("Login failed. Exiting.");
            return;
        }
        System.out.println("[Admin] Checking viewing addresses state...");
        if (!viewingAddressesUseCaseController.isExecuted()) {
            System.out.println("Viewing addresses not executed. Exiting.");
            return;
        }

        addressController.onNewAddressButtonClick(); // Admin clicks "New address"
        // Admin fills out the form.
        AddressFormDTO validFormData = new AddressFormDTO("123 Main Street");
        addressController.onSaveAddressButtonClick(validFormData); // Admin clicks "Save"

        // Scenario 2: Invalid Address Data (empty name)
        System.out.println("\n--- Scenario 2: Invalid Address Data (empty name) ---");
        addressController.onNewAddressButtonClick();
        AddressFormDTO invalidFormData = new AddressFormDTO(""); // Empty name is invalid
        addressController.onSaveAddressButtonClick(invalidFormData);

        // Scenario 3: Simulated Connection Data Error (using "error" as special name)
        System.out.println("\n--- Scenario 3: Simulated Connection Data Error ---");
        addressController.onNewAddressButtonClick();
        AddressFormDTO errorFormData = new AddressFormDTO("Error Street"); // "error" triggers SMOSConnectionException
        addressController.onSaveAddressButtonClick(errorFormData);

        // Scenario 4: User cancels operation (XC3)
        System.out.println("\n--- Scenario 4: User cancels operation ---");
        addressController.onNewAddressButtonClick();
        System.out.println("Simulating user deciding to cancel. Type 'yes' to confirm or 'no' to abort cancel:");
        addressController.onCancelButtonClick(); // Admin clicks "Cancel"

        scanner.close();
        System.out.println("\n--- Simulation Finished ---");
    }
}