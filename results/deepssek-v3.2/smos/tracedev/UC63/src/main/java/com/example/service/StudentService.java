package com.example.service;

import com.example.model.Student;
import com.example.criteria.MonitoringThresholdCriteria;

import java.util.List;

/**
 * Service interface for student‑related operations.
 */
public interface StudentService {
    /**
     * Finds students whose absences AND notes exceed the given thresholds.
     */
    List<Student> findStudentsExceedingThreshold(MonitoringThresholdCriteria criteria);
}