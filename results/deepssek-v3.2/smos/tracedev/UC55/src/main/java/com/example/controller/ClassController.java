package com.example.controller;

import com.example.dto.ClassDTO;
import com.example.dto.StudentDTO;
import com.example.repository.ClassRepository;
import com.example.model.Class;
import com.example.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for class-related operations.
 * Handles viewing classes, getting class details, and selecting classes.
 */
public class ClassController {
    private ClassRepository classRepository;

    public ClassController(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<ClassDTO> viewATAClasses() {
        // In real implementation, this would fetch all ATA classes
        // For demonstration, returning empty list
        return new ArrayList<>();
    }

    public ClassDTO getClassDetails(String classId) {
        Class classEntity = classRepository.findById(classId);
        if (classEntity == null) {
            return null;
        }

        // Convert entity to DTO
        List<StudentDTO> studentDTOs = classEntity.getStudents().stream()
            .map(student -> new StudentDTO(
                student.getStudentId(),
                student.getStudentName(),
                true, // default to present
                0     // default no delay
            ))
            .collect(Collectors.toList());

        return new ClassDTO(
            classEntity.getClassId(),
            classEntity.getClassName(),
            studentDTOs
        );
    }

    public boolean selectClass(String classId) {
        // Verify class exists
        Class classEntity = classRepository.findById(classId);
        return classEntity != null;
    }

    public void showStudentsPresent() {
        // Implementation for showing present students
        System.out.println("Showing present students...");
    }
}