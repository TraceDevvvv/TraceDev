package com.example.infrastructure;

import com.example.domain.Enrollment;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of IEnrollmentRepository for demonstration purposes.
 * Stores Enrollment objects in a HashMap.
 */
public class InMemoryEnrollmentRepository implements IEnrollmentRepository {
    private final Map<String, Enrollment> enrollments = new HashMap<>();

    @Override
    public void save(Enrollment enrollment) {
        System.out.println("[Repo] Saving Enrollment: " + enrollment.getEnrollmentId());
        enrollments.put(enrollment.getEnrollmentId(), enrollment);
    }
}