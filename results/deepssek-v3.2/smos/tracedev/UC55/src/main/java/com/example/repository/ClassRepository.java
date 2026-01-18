package com.example.repository;

import com.example.model.Class;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository class for managing Class entities.
 * Simulated in-memory storage for demonstration.
 */
public class ClassRepository {
    private Map<String, Class> classMap = new HashMap<>();

    public ClassRepository() {
        // Initialize with sample data for demonstration
    }

    public Class findById(String classId) {
        // In a real implementation, this would query a database
        return classMap.get(classId);
    }

    public void save(Class classObj) {
        classMap.put(classObj.getClassId(), classObj);
    }
}