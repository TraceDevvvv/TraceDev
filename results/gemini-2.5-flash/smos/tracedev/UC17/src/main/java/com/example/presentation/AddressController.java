package com.example.presentation;

import com.example.application.AddressService;
import com.example.application.IAuthService;
import com.example.dto.AddressDTO;
import com.example.dto.AddressListDTO;
import com.example.exceptions.ServiceException;

import java.util.List;

/**
 * The controller for address management, acting as an intermediary between the UI and the application service.
 * It handles user input, orchestrates data flow, and updates the view.
 */
public class AddressController {
    private AddressService addressService;
    private IAuthService authService; // Added to satisfy requirement REQ-003
    private AddressView addressView; // Reference to the view to update it

    /**
     * Constructs an AddressController with necessary serv and view.
     * @param addressService The service for business logic related to addresses.
     * @param authService The service for authentication and authorization.
     * @param addressView The view to display information and errors.
     */
    public AddressController(AddressService addressService, IAuthService authService, AddressView addressView) {
        this.addressService = addressService;
        this.authService = authService; // Dependency injection for AuthService
        this.addressView = addressView;
        System.out.println("[AddressController] Initialized with AddressService, AuthService, and AddressView.");
    }

    /**
     * Handles the request to display a list of addresses.
     * It first verifies if the user has administrator privileges.
     * If authorized, it retrieves addresses via the AddressService and updates the view.
     * If unauthorized or an error occurs, it displays an error message.
     *
     * @param userId The ID of the user requesting the address list.
     * @return An AddressListDTO if successful and authorized, otherwise null (error handled by view).
     */
    public AddressListDTO displayAddressList(String userId) {
        System.out.println("[AddressController] Received request to display address list for user: " + userId);
        AddressListDTO addressListDTO = new AddressListDTO();

        // Sequence Diagram Step: AddressController -> AuthService : isAdmin(userId)
        // Sequence Diagram Alt block: isAdmin
        if (authService.isAdmin(userId)) {
            System.out.println("[AddressController] User '" + userId + "' confirmed as Administrator.");
            try {
                // Sequence Diagram Step: AddressController -> AddressService : getAllAddresses()
                List<AddressDTO> addressDTOs = addressService.getAllAddresses();
                addressDTOs.forEach(addressListDTO::addAddress); // Populate the DTO

                System.out.println("[AddressController] Successfully retrieved " + addressListDTO.getAddresses().size() + " addresses.");
                // Sequence Diagram Step: AddressController -> View : renderAddressList(addressListDTO)
                addressView.renderAddressList(addressListDTO);
                return addressListDTO;
            } catch (ServiceException e) {
                // Sequence Diagram Alt block: connection interrupted -> ServiceException
                System.err.println("[AddressController] Error retrieving addresses from service: " + e.getMessage());
                // Sequence Diagram Step: AddressController -> View : displayErrorMessage(...)
                addressView.displayErrorMessage("Failed to load addresses: " + e.getMessage());
            } catch (Exception e) {
                // Catch any other unexpected exceptions
                System.err.println("[AddressController] An unexpected error occurred: " + e.getMessage());
                addressView.displayErrorMessage("An unexpected error occurred while displaying addresses.");
            }
        } else {
            // Sequence Diagram Alt block: !isAdmin
            System.out.println("[AddressController] User '" + userId + "' is not an Administrator. Access Denied.");
            // Sequence Diagram Step: AddressController -> View : displayErrorMessage(...)
            addressView.displayErrorMessage("Access Denied: Administrator role required to view addresses.");
        }
        return null; // In case of error or unauthorized access, no DTO is returned meaningfully
    }
}