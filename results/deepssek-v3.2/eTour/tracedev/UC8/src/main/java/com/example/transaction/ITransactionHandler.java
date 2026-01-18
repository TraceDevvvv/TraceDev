package com.example.transaction;

import com.example.dto.RefreshmentPointUpdateDTO;
import com.example.transaction.ConfirmationRequest;
import com.example.transaction.UpdateResult;

/**
 * Interface for handling confirmation and execution of updates.
 */
public interface ITransactionHandler {
    ConfirmationRequest generateConfirmationRequest(RefreshmentPointUpdateDTO updateDTO, String operatorId);
    UpdateResult executeConfirmedUpdate(String confirmationId);
}