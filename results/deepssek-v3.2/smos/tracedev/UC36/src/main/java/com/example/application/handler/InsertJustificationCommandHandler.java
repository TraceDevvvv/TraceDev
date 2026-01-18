package com.example.application.handler;

import com.example.application.command.InsertJustificationCommand;
import com.example.application.result.JustificationResult;
import com.example.application.usecase.InsertJustificationUseCase;
import com.example.domain.Absence;
import com.example.domain.Justification;
import com.example.infrastructure.persistence.AbsenceRepository;
import com.example.infrastructure.persistence.JustificationRepository;
import com.example.infrastructure.transaction.TransactionManager;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementing the InsertJustificationUseCase.
 */
public class InsertJustificationCommandHandler implements InsertJustificationUseCase {
    private JustificationRepository justificationRepository;
    private AbsenceRepository absenceRepository;
    private TransactionManager transactionManager;

    public InsertJustificationCommandHandler(JustificationRepository justificationRepository,
                                             AbsenceRepository absenceRepository,
                                             TransactionManager transactionManager) {
        this.justificationRepository = justificationRepository;
        this.absenceRepository = absenceRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public JustificationResult execute(InsertJustificationCommand command) {
        // Validate command data
        if (!validateCommand(command)) {
            return new JustificationResult(false, null, "Invalid justification data");
        }

        transactionManager.beginTransaction();
        try {
            // Business logic validation: check absence exists and is pending
            Optional<Absence> absenceOpt = absenceRepository.findById(command.getAbsenceId());
            if (absenceOpt.isEmpty()) {
                return new JustificationResult(false, null, "Absence not found");
            }
            Absence absence = absenceOpt.get();
            if (!absence.isPending()) {
                return new JustificationResult(false, null, "Absence not pending");
            }

            // Create justification entity
            LocalDate date = LocalDate.parse(command.getDate()); // Assumes date is in ISO format
            Justification justification = new Justification(date, command.getReason(), command.getAbsenceId());
            if (!justification.validate()) {
                return new JustificationResult(false, null, "Invalid justification data");
            }

            // Save justification
            Justification savedJustification = justificationRepository.save(justification);
            transactionManager.commitTransaction();
            return new JustificationResult(true, savedJustification.getId(), "Justification saved successfully");
        } catch (Exception e) {
            transactionManager.rollbackTransaction();
            // If persistence exception (e.g., SMOS server connection interrupted) propagate message
            return new JustificationResult(false, null, "Failed to save justification: " + e.getMessage());
        }
    }

    /**
     * Validates the command fields.
     * Assumption: date must be parseable, reason not empty, IDs not null.
     */
    private boolean validateCommand(InsertJustificationCommand command) {
        if (command.getDate() == null || command.getReason() == null ||
            command.getAbsenceId() == null || command.getAdministratorId() == null) {
            return false;
        }
        try {
            LocalDate.parse(command.getDate());
        } catch (Exception e) {
            return false;
        }
        if (command.getReason().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}