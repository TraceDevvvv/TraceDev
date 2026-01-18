package com.convention.request.controller;

import com.convention.request.exception.AgencyCommunicationException;
import com.convention.request.exception.InvalidConventionDataException;
import com.convention.request.model.Convention;
import com.convention.request.model.ConventionRequestDTO;
import com.convention.request.model.ConventionStatus;
import com.convention.request.service.ConventionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller for handling Convention-related requests.
 * This controller exposes endpoints for creating, retrieving, confirming, and canceling convention requests.
 */
@RestController
@RequestMapping("/api/conventions")
@Slf4j
@Validated // Enable validation for path variables and request parameters if needed
public class ConventionController {

    private final ConventionService conventionService;

    /**
     * Constructor for ConventionController, injecting the ConventionService.
     *
     * @param conventionService The service layer for convention business logic.
     */
    @Autowired
    public ConventionController(ConventionService conventionService) {
        this.conventionService = conventionService;
    }

    /**
     * Endpoint to request a new convention.
     * This method receives convention data, validates it, and initiates the convention creation process.
     *
     * @param request The ConventionRequestDTO containing the details of the convention to be created.
     * @return A ResponseEntity containing the created Convention object and HTTP status 201 (Created).
     * @throws InvalidConventionDataException if the provided data is invalid or insufficient.
     */
    @PostMapping
    public ResponseEntity<Convention> requestConvention(@Valid @RequestBody ConventionRequestDTO request) {
        log.info("Received request to create a new convention for agency: {}", request.getAgencyName());
        try {
            Convention newConvention = conventionService.createConvention(request);
            log.info("Convention request created successfully with ID: {}", newConvention.getConventionId());
            return new ResponseEntity<>(newConvention, HttpStatus.CREATED);
        } catch (InvalidConventionDataException e) {
            log.error("Validation error during convention creation: {}", e.getMessage());
            throw e; // Let Spring's @ResponseStatus handle the HTTP status
        }
    }

    /**
     * Endpoint to retrieve a convention by its ID.
     *
     * @param conventionId The unique identifier of the convention.
     * @return A ResponseEntity containing the Convention object and HTTP status 200 (OK).
     * @throws InvalidConventionDataException if no convention is found with the given ID.
     */
    @GetMapping("/{conventionId}")
    public ResponseEntity<Convention> getConvention(@PathVariable String conventionId) {
        log.info("Received request to get convention with ID: {}", conventionId);
        Convention convention = conventionService.getConventionById(conventionId);
        log.info("Found convention with ID: {}", conventionId);
        return new ResponseEntity<>(convention, HttpStatus.OK);
    }

    /**
     * Endpoint to confirm a convention request and send it to the external agency.
     * This simulates the "Confirm the operation" step and "Send the request to the Convention" step.
     *
     * @param conventionId The ID of the convention to confirm and send.
     * @return A ResponseEntity containing the updated Convention object and HTTP status 202 (Accepted).
     * @throws InvalidConventionDataException if the convention is not found or cannot be sent.
     * @throws AgencyCommunicationException if there's an issue communicating with the external agency.
     */
    @PostMapping("/{conventionId}/confirm")
    public ResponseEntity<Convention> confirmConvention(@PathVariable String conventionId) {
        log.info("Received request to confirm and send convention with ID: {} to agency.", conventionId);
        Convention convention = conventionService.getConventionById(conventionId); // Fetch current state
        conventionService.sendConventionToAgency(convention); // This also updates status to SENT_TO_AGENCY
        Convention updatedConvention = conventionService.getConventionById(conventionId); // Fetch updated state
        log.info("Convention ID {} confirmed and sent to agency. Current status: {}", conventionId, updatedConvention.getStatus());
        return new ResponseEntity<>(updatedConvention, HttpStatus.ACCEPTED);
    }

    /**
     * Endpoint to cancel a convention request.
     *
     * @param conventionId The ID of the convention to cancel.
     * @return A ResponseEntity containing the updated (cancelled) Convention object and HTTP status 200 (OK).
     * @throws InvalidConventionDataException if the convention is not found or cannot be cancelled.
     */
    @DeleteMapping("/{conventionId}")
    public ResponseEntity<Convention> cancelConvention(@PathVariable String conventionId) {
        log.info("Received request to cancel convention with ID: {}", conventionId);
        Convention cancelledConvention = conventionService.cancelConvention(conventionId);
        log.info("Convention ID {} successfully cancelled. Current status: {}", conventionId, cancelledConvention.getStatus());
        return new ResponseEntity<>(cancelledConvention, HttpStatus.OK);
    }

    // Global exception handlers can be added here or in a @ControllerAdvice class
    // For example, to catch InvalidConventionDataException and return a specific error structure.
    // @ExceptionHandler(InvalidConventionDataException.class)
    // public ResponseEntity<ErrorResponse> handleInvalidConventionDataException(InvalidConventionDataException ex) {
    //     ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    //     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    // }
}