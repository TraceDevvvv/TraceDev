package com.example.command;

import com.example.dto.OperationResult;
import com.example.repository.CulturalHeritageRepository;

/**
 * Command pattern implementation for deleting cultural heritage
 */
public class DeleteHeritageCommand {
    private String heritageId;
    private CulturalHeritageRepository repository;

    public DeleteHeritageCommand(String id, CulturalHeritageRepository repository) {
        this.heritageId = id;
        this.repository = repository;
    }

    public OperationResult execute() {
        try {
            boolean deleted = repository.delete(heritageId);
            if (deleted) {
                return new OperationResult(true, "Cultural heritage deleted successfully");
            } else {
                return new OperationResult(false, "Cultural heritage not found or could not be deleted");
            }
        } catch (Exception e) {
            return new OperationResult(false, "Error deleting cultural heritage: " + e.getMessage());
        }
    }
}