
package com.example.app.infrastructure;

import com.example.app.domain.ArchivedClass;
import com.example.app.application.DeletionFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IClassRepository, simulating persistence operations.
 * This implementation uses an in-memory map to store classes and simulates
 * database transactions and connection issues.
 */
public class ClassRepositoryImpl implements IClassRepository {
    // Represents a connection to the actual archive/DB. For this simulation, it's an Object.
    private Object archiveConnection;
    // In-memory "database" to store ArchivedClass objects.
    private Map<String, ArchivedClass> classDb;

    // Flags to simulate different error conditions as per sequence diagram
    private boolean simulateConnectionInterruptOnDelete = false;
    private boolean simulateConnectionInterruptOnFindAll = false;
    private boolean simulateDeletionFailure = false;

    /**
     * Constructor for ClassRepositoryImpl.
     * Initializes the in-memory database and a dummy connection.
     *
     * @param initialClasses A map of initial classes to populate the repository.
     */
    public ClassRepositoryImpl(Map<String, ArchivedClass> initialClasses) {
        this.archiveConnection = new Object(); // Dummy connection object
        this.classDb = new HashMap<>(initialClasses);
    }

    /**
     * Allows setting a flag to simulate a connection interruption during the delete operation.
     * @param simulate true to simulate interruption, false otherwise.
     */
    public void setSimulateConnectionInterruptOnDelete(boolean simulate) {
        this.simulateConnectionInterruptOnDelete = simulate;
    }

    /**
     * Allows setting a flag to simulate a connection interruption during the findAll operation.
     * @param simulate true to simulate interruption, false otherwise.
     */
    public void setSimulateConnectionInterruptOnFindAll(boolean simulate) {
        this.simulateConnectionInterruptOnFindAll = simulate;
    }

    /**
     * Allows setting a flag to simulate a deletion failure (e.g., integrity issue).
     * @param simulate true to simulate deletion failure, false otherwise.
     */
    public void setSimulateDeletionFailure(boolean simulate) {
        this.simulateDeletionFailure = simulate;
    }

    @Override
    public void delete(String classId) throws ConnectionInterruptedException {
        System.out.println("RepoImpl: Attempting to delete class with ID: " + classId);
        if (simulateConnectionInterruptOnDelete) {
            System.out.println("RepoImpl: Simulating ConnectionInterruptedException during delete.");
            throw new ConnectionInterruptedException("Connection to archive interrupted during delete operation for class " + classId);
        }

        // Simulate transaction management (REQ-002)
        beginTransaction();
        try {
            // Simulate executeDeleteSQL()
            if (classDb.containsKey(classId)) {
                if (simulateDeletionFailure) {
                    System.out.println("RepoImpl: Simulating DeletionFailedException (integrity issue).");
                    // Do not actually remove if simulation fails
                    throw new DeletionFailedException("Simulated integrity issue or deletion failure for class " + classId);
                } else {
                    classDb.remove(classId);
                    System.out.println("RepoImpl: executeDeleteSQL for " + classId + " - SUCCESS");
                    // Traceability: m9 (delete_success)
                    commitTransaction(); // Transaction committed on success
                    System.out.println("RepoImpl: Transaction committed for deletion of " + classId);
                }
            } else {
                System.out.println("RepoImpl: Class with ID " + classId + " not found. Deletion considered successful (no change).");
                commitTransaction(); // If not found, no change, commit.
            }
        } catch (DeletionFailedException e) {
            rollbackTransaction(); // Transaction rolled back on failure
            System.out.println("RepoImpl: Transaction rolled back for deletion of " + classId);
            // The IClassRepository interface does not declare DeletionFailedException,
            // so we wrap it in an unchecked exception to satisfy the interface contract.
            throw new RuntimeException("Deletion failed for class " + classId + ": " + e.getMessage(), e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions and rollback
            rollbackTransaction();
            System.err.println("RepoImpl: Unexpected error during deletion, transaction rolled back: " + e.getMessage());
            throw new RuntimeException("Unexpected error during deletion for class " + classId, e);
        }
    }

    @Override
    public ArchivedClass findById(String classId) throws ConnectionInterruptedException {
        System.out.println("RepoImpl: Attempting to find class by ID: " + classId);
        // This findById is not part of the main delete flow's error simulation in the sequence diagram,
        // but it could potentially throw a connection error in other use cases.
        // For this SD, we don't have an explicit 'alt' for findById connection issues.
        // However, findAll() has it, so we'll apply connection logic to findAll().
        return classDb.get(classId);
    }

    @Override
    public List<ArchivedClass> findAll() throws ConnectionInterruptedException {
        System.out.println("RepoImpl: Attempting to find all classes.");
        if (simulateConnectionInterruptOnFindAll) {
            System.out.println("RepoImpl: Simulating ConnectionInterruptedException during findAll.");
            throw new ConnectionInterruptedException("Connection to archive interrupted during findAll operation.");
        }
        // Simulate executeSelectAllSQL()
        System.out.println("RepoImpl: executeSelectAllSQL - SUCCESS");
        // Traceability: m15 (allClassesData)
        return new ArrayList<>(classDb.values());
    }

    /**
     * Simulates starting a database transaction.
     */
    private void beginTransaction() {
        System.out.println("ArchiveDB: beginTransaction()");
        // In a real application, this would involve JDBC connection.setAutoCommit(false)
    }

    /**
     * Simulates committing a database transaction.
     */
    private void commitTransaction() {
        System.out.println("ArchiveDB: commitTransaction()");
        // In a real application, this would involve JDBC connection.commit()
    }

    /**
     * Simulates rolling back a database transaction.
     */
    private void rollbackTransaction() {
        System.out.println("ArchiveDB: rollbackTransaction()");
        // In a real application, this would involve JDBC connection.rollback()
    }
}
