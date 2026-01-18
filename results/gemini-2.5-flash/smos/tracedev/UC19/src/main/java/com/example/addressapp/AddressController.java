package com.example.addressapp;

/**
 * Controller for handling requests related to Addresses.
 * Manages the flow between UI and service layers.
 */
public class AddressController {
    private AddressService addressService;
    private AddressListUI addressListUI; // Manages by controller
    private AddressDetailsUI addressDetailsUI; // Manages by controller
    private UserSession userSession; // Checks status, added for EC1

    /**
     * Constructs an AddressController with necessary dependencies.
     * @param addressService The service for business logic.
     * @param addressListUI The UI for listing addresses.
     * @param addressDetailsUI The UI for displaying address details.
     * @param userSession The session object to check login status.
     */
    public AddressController(AddressService addressService, AddressListUI addressListUI, AddressDetailsUI addressDetailsUI, UserSession userSession) {
        this.addressService = addressService;
        this.addressListUI = addressListUI;
        this.addressDetailsUI = addressDetailsUI;
        this.userSession = userSession;
    }

    /**
     * Displays details for a specific address.
     * This method implements the main flow of the sequence diagram, including
     * user session check (EC1) and error handling.
     * Corresponds to the 'requestAddressDetails' message in the sequence diagram.
     * @param addressId The ID of the address whose details are to be shown.
     */
    public void requestAddressDetails(String addressId) {
        System.out.println("AddressController: Received request to show details for ID: " + addressId);

        // EC1: Check if the user is logged in
        if (!userSession.isLoggedIn()) {
            System.out.println("AddressController: User not logged in, displaying error.");
            addressDetailsUI.displayError("User not logged in. Please log in to view address details.");
            return;
        }

        // Precondition EC2: Assumed to be handled by AddressListUI interacting with Administrator.
        // The Administrator has performed 'ViewingLenchIndirizzi' to reach this point.
        System.out.println("AddressController: User is logged in. Proceeding to fetch address details.");

        // Call the service to get address details
        AddressDetailsDTO dto = addressService.getAddressDetails(addressId);

        if (dto != null) {
            System.out.println("AddressController: Address details successfully retrieved, displaying in UI.");
            addressDetailsUI.displayAddressDetails(dto); // Successful retrieval path
        } else {
            System.out.println("AddressController: Failed to retrieve address details, displaying error in UI.");
            // Error path (address not found or connection issue)
            addressDetailsUI.displayError("Could not retrieve address details. Please try again later.");
        }
    }
}