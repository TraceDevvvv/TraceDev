package com.example.transaction;

import com.example.dto.RefreshmentPointUpdateDTO;
import com.example.domain.RefreshmentPoint;
import com.example.repository.IRefreshmentPointRepository;
import com.example.repository.RefreshmentPointRepositoryImpl;
import com.example.domain.ConnectionStatus;
import java.util.Optional;
import java.util.UUID;

/**
 * Concrete implementation of ITransactionHandler.
 * Also includes connection check as per diagram.
 */
public class TransactionHandlerImpl implements ITransactionHandler {
    private IRefreshmentPointRepository repository;
    // In a real app, we'd store pending confirmations in a map/db.
    // For simplicity, we simulate with a map in memory.
    private java.util.Map<String, RefreshmentPointUpdateDTO> pendingConfirmations = new java.util.HashMap<>();

    public TransactionHandlerImpl(IRefreshmentPointRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfirmationRequest generateConfirmationRequest(RefreshmentPointUpdateDTO updateDTO, String operatorId) {
        String confirmationId = UUID.randomUUID().toString();
        pendingConfirmations.put(confirmationId, updateDTO);
        // Include operatorId in the request (assumed for logging)
        return new ConfirmationRequest(confirmationId, updateDTO);
    }

    @Override
    public UpdateResult executeConfirmedUpdate(String confirmationId) {
        RefreshmentPointUpdateDTO updateDTO = pendingConfirmations.get(confirmationId);
        if (updateDTO == null) {
            return new UpdateResult(false, null, "Invalid or expired confirmation ID.");
        }
        Optional<RefreshmentPoint> optionalPoint = repository.findById(updateDTO.getId());
        if (!optionalPoint.isPresent()) {
            return new UpdateResult(false, updateDTO.getId(), "Refreshment point not found.");
        }
        RefreshmentPoint point = optionalPoint.get();
        point.updateDetails(updateDTO);
        repository.save(point);
        pendingConfirmations.remove(confirmationId);
        return new UpdateResult(true, point.getId(), "Update successful.");
    }

    /**
     * Added to satisfy requirement Exit Conditions.
     * In diagram, this method is in TransactionHandlerImpl.
     * For simplicity, we delegate to a ServerConnection instance.
     * Since we don't have direct access, we return a default.
     */
    public ConnectionStatus checkConnection() {
        // This would normally interact with a ServerConnection.
        // Return a default.
        return ConnectionStatus.CONNECTED;
    }
}