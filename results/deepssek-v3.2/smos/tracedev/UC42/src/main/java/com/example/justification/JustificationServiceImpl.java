
package com.example.justification;

import java.util.Date;
import com.example.audit.AuditLogger;

/**
 * Implementation of JustificationService.
 */
public class JustificationServiceImpl implements JustificationService {
    private JustificationRepository justificationRepository;
    private AuditLogger auditLogger;

    // Constructor for dependency injection
    public JustificationServiceImpl(JustificationRepository justificationRepository, AuditLogger auditLogger) {
        this.justificationRepository = justificationRepository;
        this.auditLogger = auditLogger;
    }

    @Override
    public boolean deleteJustification(int justificationId) {
        // Find the justification first
        Justification justification = justificationRepository.findById(justificationId);
        if (justification == null) {
            // Justification not found - in real scenario might throw exception
            // For sequence diagram error handling we could throw IllegalArgumentException
            // but returning false per sequence diagram alternative flow.
            return false;
        }

        try {
            // Perform deletion
            justificationRepository.delete(justification);
            // Log deletion - using current timestamp and assuming adminId is retrieved elsewhere
            // Here we pass 0 as placeholder adminId; real implementation would receive it from caller.
            auditLogger.logDeletion(justificationId, 0, new Date());
            return true;
        } catch (Exception e) {
            // Handle any exceptions (like database connection issues)
            // According to sequence diagram, return false on failure.
            return false;
        }
    }

    @Override
    public JustificationDTO getJustificationDetails(int justificationId) {
        Justification justification = justificationRepository.findById(justificationId);
        if (justification == null) {
            // Could throw exception, but return null as per diagram (null leads to IllegalArgumentException in controller)
            return null;
        }
        return JustificationDTO.fromEntity(justification);
    }

    @Override
    public boolean cancelOperation(int justificationId) {
        // Cancel deletion transaction rollback
        justificationRepository.cancelTransaction(justificationId);
        // Return true indicating cancellation succeeded
        return true;
    }

    /**
     * Pre-deletion validation as per sequence diagram note m6.
     * This method is called by the Controller before deletion.
     * @param justificationId the ID of the justification
     * @return true if validation passes, false otherwise
     */
    public boolean preDeletionValidation(int justificationId) {
        // Validation logic: ensure justification exists and is deletable.
        Justification justification = justificationRepository.findById(justificationId);
        return justification != null;
    }
}
