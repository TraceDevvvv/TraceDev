package com.example.usecase;

import com.example.repository.CulturalObjectRepository;

/**
 * Use case responsible for orchestrating the deletion of a CulturalObject.
 * This class corresponds to the 'DeleteCulturalObjectUseCase' class in the Class Diagram.
 */
public class DeleteCulturalObjectUseCase {
    private final CulturalObjectRepository culturalObjectRepository;

    /**
     * Constructs a new DeleteCulturalObjectUseCase.
     * @param repo The repository for managing cultural objects.
     */
    public DeleteCulturalObjectUseCase(CulturalObjectRepository repo) {
        this.culturalObjectRepository = repo;
    }

    /**
     * Executes the deletion of a cultural object.
     * This method interacts with the CulturalObjectRepository to perform the delete operation.
     * @param objectId The ID of the cultural object to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean execute(String objectId) {
        System.out.println("[UseCase] Attempting to execute deletion for object ID: " + objectId);
        // Calls Repository to delete the object
        boolean success = culturalObjectRepository.delete(objectId);
        if (success) {
            System.out.println("[UseCase] Deletion successful for object ID: " + objectId);
        } else {
            System.err.println("[UseCase] Deletion failed for object ID: " + objectId);
        }
        return success;
    }
}