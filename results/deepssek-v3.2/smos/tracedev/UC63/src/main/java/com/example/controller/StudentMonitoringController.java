package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.model.Student;
import com.example.service.StudentService;
import com.example.criteria.MonitoringThresholdCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the student monitoring use case.
 * Orchestrates the flow between view and service.
 */
public class StudentMonitoringController {
    private StudentService studentService;

    public StudentMonitoringController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Retrieves students exceeding both absences and notes thresholds,
     * converts them to DTOs, and returns the list.
     */
    public List<StudentDTO> monitorStudentsWithThreshold(MonitoringThresholdCriteria criteria) {
        List<Student> filteredStudents = studentService.findStudentsExceedingThreshold(criteria);
        List<StudentDTO> result = new ArrayList<>();
        for (Student student : filteredStudents) {
            int absences = 0; // Placeholder, should be obtained from service
            int notes = 0;    // Placeholder, should be obtained from service
            StudentDTO dto = convertToDTO(student, absences, notes);
            result.add(dto);
        }
        return result;
    }

    /**
     * Converts a Student entity to a StudentDTO.
     */
    public StudentDTO convertToDTO(Student student, int absences, int notes) {
        return new StudentDTO(student, absences, notes);
    }
}