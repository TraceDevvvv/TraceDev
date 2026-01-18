package com.example.infrastructure;

import com.example.exception.RepositoryAccessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concrete implementation of IParentStudentRepository.
 * This implementation simulates a database by using in-memory maps.
 * It also simulates connection issues for demonstration purposes.
 */
public class ParentStudentRepository implements IParentStudentRepository {

    // Simulates a database table mapping parentId to a list of associated studentIds
    private final Map<String, List<String>> parentStudentAssociations;
    private boolean simulateConnectionLoss = false;

    public ParentStudentRepository() {
        this.parentStudentAssociations = new ConcurrentHashMap<>(); // Thread-safe for simplicity
        // Seed some dummy data
        parentStudentAssociations.put("P1", new ArrayList<>(List.of("S1", "S2")));
        parentStudentAssociations.put("P2", new ArrayList<>(List.of("S3")));
        parentStudentAssociations.put("P3", new ArrayList<>());
    }

    /**
     * Sets whether the repository should simulate connection loss for testing purposes.
     * @param simulateConnectionLoss True to simulate connection loss, false otherwise.
     */
    public void setSimulateConnectionLoss(boolean simulateConnectionLoss) {
        this.simulateConnectionLoss = simulateConnectionLoss;
        System.out.println("[Repository] Simulating connection loss: " + simulateConnectionLoss);
    }

    @Override
    public List<String> getAssociatedStudentIds(String parentId) throws RepositoryAccessException {
        // Sequence Diagram: m5, m25 - Repository to DB: SELECT student_id FROM parent_student_associations WHERE parent_id = parentId
        if (simulateConnectionLoss) {
            throw new RepositoryAccessException("DB connection lost during retrieval for parent: " + parentId);
        }
        System.out.println("[Repository] Retrieving associated student IDs for parent: " + parentId);
        // Simulate SELECT student_id FROM parent_student_associations WHERE parent_id = parentId
        List<String> studentIds = parentStudentAssociations.getOrDefault(parentId, new ArrayList<>());
        return new ArrayList<>(studentIds); // Return a copy to prevent external modification
    }

    @Override
    public void addAssociation(String parentId, String studentId) throws RepositoryAccessException {
        // Sequence Diagram: m35 - Repository to DB: INSERT INTO parent_student_associations (parent_id, student_id) VALUES (parentId, studentId)
        if (simulateConnectionLoss) {
            throw new RepositoryAccessException("DB connection lost during add operation for parent: " + parentId + ", student: " + studentId);
        }
        System.out.println("[Repository] Adding association: Parent=" + parentId + ", Student=" + studentId);
        // Simulate INSERT INTO parent_student_associations (parent_id, student_id) VALUES (parentId, studentId)
        parentStudentAssociations.computeIfAbsent(parentId, k -> new ArrayList<>()).add(studentId);
        // Sequence Diagram: m36 - DB to Repository: acknowledgement (implicit return)
    }

    @Override
    public void removeAssociation(String parentId, String studentId) throws RepositoryAccessException {
        // Sequence Diagram: m31 - Repository to DB: DELETE FROM parent_student_associations WHERE parent_id = parentId AND student_id = studentId
        if (simulateConnectionLoss) {
            throw new RepositoryAccessException("DB connection lost during remove operation for parent: " + parentId + ", student: " + studentId);
        }
        System.out.println("[Repository] Removing association: Parent=" + parentId + ", Student=" + studentId);
        // Simulate DELETE FROM parent_student_associations WHERE parent_id = parentId AND student_id = studentId
        List<String> studentIds = parentStudentAssociations.get(parentId);
        if (studentIds != null) {
            studentIds.remove(studentId);
        }
        // Sequence Diagram: m32 - DB to Repository: acknowledgement (implicit return)
    }
}