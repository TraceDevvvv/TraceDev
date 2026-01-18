package com.example.attendancesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of StudentRepository for demonstration purposes.
 * Simulates data storage and retrieval without a real database.
 */
public class InMemoryStudentRepository implements StudentRepository {
    // Stores students organized by class ID
    private Map<String, List<Student>> studentsByClass = new HashMap<>();

    /**
     * Initializes the repository with some sample student data.
     */
    public InMemoryStudentRepository() {
        studentsByClass.put("CLASS_A", List.of(
            new Student("S001", "Alice Smith", "alice.parent@example.com", "CLASS_A"),
            new Student("S002", "Bob Johnson", "bob.parent@example.com", "CLASS_A"),
            new Student("S003", "Charlie Brown", "charlie.parent@example.com", "CLASS_A")
        ));
        studentsByClass.put("CLASS_B", List.of(
            new Student("S004", "Diana Prince", "diana.parent@example.com", "CLASS_B"),
            new Student("S005", "Clark Kent", "clark.parent@example.com", "CLASS_B")
        ));
    }

    /**
     * Finds students by class ID.
     * @param classId The ID of the class.
     * @return A list of students; an empty list if no students found for the class.
     */
    @Override
    public List<Student> findStudentsByClass(String classId) {
        System.out.println("[StudentRepository] Finding students for class: " + classId);
        // Simulate a small delay for realism
        try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return studentsByClass.getOrDefault(classId, new ArrayList<>());
    }
}