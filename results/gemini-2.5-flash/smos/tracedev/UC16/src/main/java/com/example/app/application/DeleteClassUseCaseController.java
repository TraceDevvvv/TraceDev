
package com.example.app.application;

import com.example.app.application.DeletionFailedException;
import com.example.app.domain.ArchivedClass;
import com.example.app.infrastructure.IClassRepository;
import com.example.app.infrastructure.ConnectionInterruptedException;

import java.util.Collections;
import java.util.List;

/**
 * Controls the use case for deleting a class from the archive.
 * It orchestrates the deletion process, handles exceptions, and retrieves
 * an updated list of classes.
 */
public class DeleteClassUseCaseController {
    private final IClassRepository classRepository;

    /**
     * Constructs a DeleteClassUseCaseController with a dependency on IClassRepository.
     * Dependency injection is used here.
     *
     * @param classRepository The repository interface for class data operations.
     */
    public DeleteClassUseCaseController(IClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Orchestrates the deletion of a class and retrieves the updated list of classes.
     * Handles various exceptions that can occur during the process.
     *
     * @param classId The ID of the class to be deleted.
     * @return A list of all classes after the deletion attempt, or an empty list
     *         if retrieval of the updated list fails.
     * @throws DeletionFailedException if the deletion itself fails due to integrity issues.
     * @throws ConnectionInterruptedException if a connection issue prevents deletion or retrieval.
     */
    public List<ArchivedClass> deleteClass(String classId)
            throws DeletionFailedException, ConnectionInterruptedException {
        // Traceability: m5 (Orchestrates class deletion and ensures integrity)
        System.out.println("UCC: deleteClass called for ID: " + classId);

        List<ArchivedClass> updatedClassList = Collections.emptyList();

        try {
            // Attempt to delete the class
            classRepository.delete(classId);
            System.out.println("UCC: Class " + classId + " deleted successfully.");

            // If deletion was successful, retrieve the updated list
            // Traceability: m11 (Deletion successful, retrieve updated list)
            System.out.println("UCC: Deletion successful, now retrieving updated list.");
            try {
                updatedClassList = classRepository.findAll();
                System.out.println("UCC: Successfully retrieved updated class list.");
            } catch (ConnectionInterruptedException e) {
                // REQ-001: Handle connection interruption during findAll after successful delete
                System.err.println("UCC: Connection interrupted while retrieving updated list after deletion. " + e.getMessage());
                // This exception will be caught by the UI and trigger an error message
                throw e; // Re-throw to be handled by the UI layer
            }
        } catch (DeletionFailedException e) {
            // REQ-002: Handle deletion failure due to integrity issues
            System.err.println("UCC: Deletion failed for class " + classId + " due to data integrity. " + e.getMessage());
            throw e; // Re-throw to be handled by the UI layer
        } catch (ConnectionInterruptedException e) {
            // REQ-001: Handle connection interruption during the initial delete operation
            System.err.println("UCC: Connection interrupted during deletion for class " + classId + ". " + e.getMessage());
            throw e; // Re-throw to be handled by the UI layer
        }

        return updatedClassList;
    }
}
