package com.example.repository;

import com.example.model.Student;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository class for managing Student entities.
 * Simulated in-memory storage for demonstration.
 */
public class StudentRepository {
    private Map<String, Student> studentMap = new HashMap<>();

    public Student findById(String studentId) {
        // In a real implementation, this would query a database
        return studentMap.get(studentId);
    }

    public void save(Student studentObj) {
        studentMap.put(studentObj.getStudentId(), studentObj);
    }
}