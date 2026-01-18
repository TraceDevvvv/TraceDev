package smos.presentation;

import smos.system.*;
import smos.login.LoginService;
import smos.dettaglio.ViewDettaglizzazioneUseCase;

/**
 * Controller for handling address deletion requests.
 * Enforces entry conditions and timing constraints.
 */
public class DeleteAddressController {
    private AddressDeletionUseCase addressDeletionUseCase;
    private LoginService loginService;
    private ViewDettaglizzazioneUseCase viewDettaglizzazioneUseCase;

    // Constructor with useCase only (as per class diagram)
    public DeleteAddressController(AddressDeletionUseCase useCase) {
        this.addressDeletionUseCase = useCase;
        // For entry conditions, we assume these are injected elsewhere.
        this.loginService = new LoginService();
        this.viewDettaglizzazioneUseCase = new ViewDettaglizzazioneUseCase();
    }

    /**
     * Deletes an address after validating entry conditions.
     * Implements timing constraint: response < 3 seconds.
     * @param addressId the address identifier
     * @return response with success status, message, and updated addresses list
     */
    public DeleteAddressResponse deleteAddress(String addressId) {
        long startTime = System.currentTimeMillis();

        // Entry condition 1: Administrator IS logged in
        if (!loginService.isLoggedIn()) {
            return new DeleteAddressResponse(false, "Administrator not logged in", null);
        }

        // Entry condition 2: Administrator HAS taken ViewDettaglizzazione
        // We assume the use case provides a method to check this.
        // For simplicity, we call execute which might throw if not taken.
        // We'll assume a method isTaken() exists; here we simulate.
        boolean hasTaken = viewDettaglizzazioneUseCase.isTaken();
        if (!hasTaken) {
            return new DeleteAddressResponse(false, "ViewDettaglizzazione use case not taken", null);
        }

        // Perform deletion
        DeleteAddressResult result = addressDeletionUseCase.execute(addressId);

        // Handle results as per sequence diagram
        DeleteAddressResponse response;
        switch (result) {
            case ADDRESS_NOT_FOUND:
                response = new DeleteAddressResponse(false, "Address not found", null);
                break;
            case ADDRESS_HAS_ASSOCIATED_CLASSES:
                ErrorMessage errorMessage = new ErrorMessage("Unable to delete the address, delete\n the associated classes and try again");
                response = new DeleteAddressResponse(false, errorMessage.toString(), null);
                break;
            case ADDRESS_DELETED_SUCCESSFULLY:
                // Retrieve updated addresses list after successful deletion
                java.util.List<Address> updatedList = addressDeletionUseCase.getUpdatedAddresses();
                response = new DeleteAddressResponse(true, "Address deleted", updatedList);
                break;
            default:
                response = new DeleteAddressResponse(false, "Unknown error", null);
                break;
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        if (duration >= 3000) {
            System.err.println("Warning: Response time exceeded 3 seconds: " + duration + "ms");
        }

        return response;
    }
}