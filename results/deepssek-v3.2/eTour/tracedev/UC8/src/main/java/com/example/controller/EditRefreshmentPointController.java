package com.example.controller;

import com.example.repository.IRefreshmentPointRepository;
import com.example.validation.IDataValidator;
import com.example.transaction.ITransactionHandler;
import com.example.service.AuthenticationService;
import com.example.service.ServerConnection;
import com.example.domain.RefreshmentPoint;
import com.example.domain.RefreshmentPointStatus;
import com.example.domain.ConnectionStatus;
import com.example.dto.*;
import com.example.validation.ValidationResult;
import com.example.transaction.ConfirmationRequest;
import com.example.transaction.UpdateResult;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Use Case Controller coordinating the edit refreshment point flow.
 * Implements all interactions as per sequence diagram.
 */
public class EditRefreshmentPointController {
    private IRefreshmentPointRepository refreshmentPointRepository;
    private IDataValidator dataValidator;
    private ITransactionHandler transactionHandler;
    private AuthenticationService authenticationService;
    private ServerConnection serverConnection;

    public EditRefreshmentPointController(IRefreshmentPointRepository repository,
                                          IDataValidator validator,
                                          ITransactionHandler handler,
                                          AuthenticationService authService,
                                          ServerConnection connection) {
        this.refreshmentPointRepository = repository;
        this.dataValidator = validator;
        this.transactionHandler = handler;
        this.authenticationService = authService;
        this.serverConnection = connection;
    }

    /**
     * Retrieves list of active refreshment points as summary DTOs.
     */
    public List<RefreshmentPointSummaryDTO> getRefreshmentPointList() {
        List<RefreshmentPoint> points = refreshmentPointRepository.findAllActive();
        return points.stream()
                .map(p -> new RefreshmentPointSummaryDTO(p.getId(), p.getName(), p.getLocation(), p.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Loads details of a specific refreshment point by ID.
     */
    public RefreshmentPointDetailsDTO loadRefreshmentPointDetails(String pointId) {
        Optional<RefreshmentPoint> optionalPoint = refreshmentPointRepository.findById(pointId);
        if (!optionalPoint.isPresent()) {
            throw new IllegalArgumentException("Refreshment point not found: " + pointId);
        }
        return new RefreshmentPointDetailsDTO(optionalPoint.get());
    }

    /**
     * Fetches the latest data from the point of rest (as per sequence diagram step 4).
     */
    public Map<String, Object> fetchLatestPointData(String pointId) {
        Optional<RefreshmentPoint> optionalPoint = refreshmentPointRepository.findById(pointId);
        if (!optionalPoint.isPresent()) {
            throw new IllegalArgumentException("Refreshment point not found: " + pointId);
        }
        return optionalPoint.get().fetchLatestData();
    }

    /**
     * Validates the update DTO using the validator.
     */
    public ValidationResult validateUpdateData(RefreshmentPointUpdateDTO updateDTO) {
        return dataValidator.validateUpdateDTO(updateDTO);
    }

    /**
     * Requests confirmation for an update, generating a confirmation request.
     */
    public ConfirmationRequest requestConfirmation(RefreshmentPointUpdateDTO updateDTO) {
        // Ensure operator is logged in
        if (!authenticationService.isLoggedIn()) {
            throw new IllegalStateException("Operator not logged in.");
        }
        String operatorId = authenticationService.getCurrentOperatorId();
        return transactionHandler.generateConfirmationRequest(updateDTO, operatorId);
    }

    /**
     * Executes the update after confirmation.
     */
    public UpdateResult confirmAndExecuteUpdate(String confirmationId, String operatorId) {
        // Optionally verify operator matches (not required in diagram).
        return transactionHandler.executeConfirmedUpdate(confirmationId);
    }

    /**
     * Cancels a pending update.
     * In a real implementation, would remove from pending confirmations.
     * For simplicity, we just log.
     */
    public void cancelUpdate(String confirmationId) {
        // Could remove from transaction handler's pending map.
        // For now, do nothing.
        System.out.println("Update cancelled for confirmation: " + confirmationId);
    }

    /**
     * Checks the server connection status (for exit conditions).
     */
    public ConnectionStatus checkServerConnection() {
        return serverConnection.checkConnection();
    }
}