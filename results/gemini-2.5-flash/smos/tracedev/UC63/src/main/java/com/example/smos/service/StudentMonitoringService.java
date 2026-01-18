package com.example.smos.service;

import com.example.smos.dto.StudentMonitoringDTO;
import com.example.smos.exception.SMOSServerConnectionException;
import com.example.smos.model.Student;
import com.example.smos.model.ThresholdEvaluator;
import com.example.smos.repository.IStudentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for student monitoring operations.
 * This class corresponds to the 'StudentMonitoringService' class in the UML diagram.
 * It orchestrates the process of retrieving student data, evaluating thresholds,
 * and preparing DTOs for the presentation layer.
 */
public class StudentMonitoringService {

    private final IStudentRepository studentRepository;
    private final ThresholdEvaluator thresholdEvaluator;

    /**
     * Constructs a StudentMonitoringService with required dependencies.
     * Dependencies are injected to promote loose coupling.
     *
     * @param studentRepository The repository for student data access.
     * @param thresholdEvaluator The evaluator for student thresholds.
     */
    public StudentMonitoringService(IStudentRepository studentRepository, ThresholdEvaluator thresholdEvaluator) {
        this.studentRepository = studentRepository;
        this.thresholdEvaluator = thresholdEvaluator;
    }

    /**
     * Retrieves a list of students who meet specific monitoring criteria (high absences or high notes).
     * This method implements the core logic of filtering students based on thresholds.
     *
     * @param absenceThreshold The threshold for considering absences as 'high'.
     * @param noteThreshold The threshold for considering notes as 'high'.
     * @return A list of StudentMonitoringDTO objects for students meeting the criteria.
     * @throws SMOSServerConnectionException If the underlying repository fails to connect to the server.
     *                                       This propagates the exception as per REQ-008 and the sequence diagram.
     */
    public List<StudentMonitoringDTO> getHighPerformanceStudents(int absenceThreshold, int noteThreshold)
            throws SMOSServerConnectionException {
        // Step 1: Retrieve all student data from the repository.
        // This call can throw SMOSServerConnectionException, which is propagated.
        List<Student> studentList = studentRepository.findStudents();

        // Step 2: Initialize a list to hold DTOs of students meeting monitoring criteria.
        List<StudentMonitoringDTO> filteredStudents = new ArrayList<>();

        // Step 3: Loop through each student to evaluate against thresholds.
        for (Student student : studentList) {
            // Step 3.1: Evaluate if the student meets the monitoring criteria.
            // The 'evaluate' method determines if a student has high absences OR high notes.
            boolean isHighPerformance = thresholdEvaluator.evaluate(student, absenceThreshold, noteThreshold);

            // Step 3.2: If the student meets the criteria, create a DTO and add it to the filtered list.
            if (isHighPerformance) {
                // Create a DTO with relevant student information.
                StudentMonitoringDTO studentDTO = new StudentMonitoringDTO(
                        student.getStudentId(),
                        student.getName(),
                        student.getAbsencesCount(),
                        student.getNotesCount()
                );
                filteredStudents.add(studentDTO);
            }
        }

        // Step 4: Return the list of DTOs for students meeting monitoring criteria.
        return filteredStudents;
    }
}