package com.example.addressmanager.controller;

import com.example.addressmanager.exception.AddressDeletionForbiddenException;
import com.example.addressmanager.exception.AddressNotFoundException;
import com.example.addressmanager.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing address-related operations.
 * Provides endpoints for deleting addresses.
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    /**
     * Constructs an AddressController with the necessary AddressService.
     * Spring's dependency injection automatically provides this service.
     *
     * @param addressService The service layer for address business logic.
     */
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Handles HTTP DELETE requests to delete an address by its ID.
     * This endpoint is typically invoked when an administrator clicks a "Delete" button
     * on the UI for a specific address.
     *
     * @param addressId The unique identifier of the address to be deleted.
     * @return A {@link ResponseEntity} indicating the outcome of the deletion operation.
     *         - HTTP 200 OK if the address is successfully deleted.
     *         - HTTP 404 Not Found if the address does not exist.
     *         - HTTP 409 Conflict if the address has associated classes and cannot be deleted.
     *         - HTTP 500 Internal Server Error for any other unexpected issues.
     */
    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        logger.info("Received request to delete address with ID: {}", addressId);
        addressService.deleteAddress(addressId);
        logger.info("Address with ID {} successfully deleted and SMOS connection handled.", addressId);
        return ResponseEntity.ok("Address with ID " + addressId + " deleted successfully. Updated address list should be fetched by client.");
    }

    /**
     * Exception handler for {@link AddressNotFoundException}.
     * This method catches AddressNotFoundException thrown by the service layer
     * and maps it to an HTTP 404 Not Found response.
     *
     * @param ex The AddressNotFoundException that was thrown.
     * @return A {@link ResponseEntity} with HTTP status 404 and the exception message.
     */
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException ex) {
        logger.warn("AddressNotFoundException caught: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for {@link AddressDeletionForbiddenException}.
     * This method catches AddressDeletionForbiddenException thrown by the service layer
     * and maps it to an HTTP 409 Conflict response.
     *
     * @param ex The AddressDeletionForbiddenException that was thrown.
     * @return A {@link ResponseEntity} with HTTP status 409 and the exception message.
     */
    @ExceptionHandler(AddressDeletionForbiddenException.class)
    public ResponseEntity<String> handleAddressDeletionForbiddenException(AddressDeletionForbiddenException ex) {
        logger.warn("AddressDeletionForbiddenException caught: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Generic exception handler for any other unexpected exceptions.
     * This method catches any {@link Exception} not specifically handled
     * and maps it to an HTTP 500 Internal Server Error response.
     *
     * @param ex The Exception that was thrown.
     * @return A {@link ResponseEntity} with HTTP status 500 and a generic error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}