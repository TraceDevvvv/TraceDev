package com.example.school;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple mock service to resolve student IDs to student names.
 * This simulates a lookup service that would typically be a separate repository or service.
 * Assumption: Not explicitly in UML, but needed for DTO mapping.
 */
public class StudentNameResolver {
    private final Map<String, String> studentIdToNameMap;

    public StudentNameResolver() {
        // Mock data for student IDs and names
        studentIdToNameMap = new HashMap<>();
        studentIdToNameMap.put("S1001", "Alice Smith");
        studentIdToNameMap.put("S1002", "Bob Johnson");
        studentIdToNameMap.put("S1003", "Charlie Brown");
        studentIdToNameMap.put("S1004", "Diana Prince");
        // Add more mock data as needed
    }

    /**
     * Resolves a student ID to their corresponding name.
     * @param studentId The ID of the student.
     * @return The student's name, or "Unknown Student" if not found.
     */
    public String getStudentName(String studentId) {
        return studentIdToNameMap.getOrDefault(studentId, "Unknown Student (" + studentId + ")");
    }
}