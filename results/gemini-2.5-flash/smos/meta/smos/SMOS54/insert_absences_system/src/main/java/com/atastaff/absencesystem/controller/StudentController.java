package com.atastaff.absencesystem.controller;

import com.atastaff.absencesystem.model.Student;
import com.atastaff.absencesystem.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for handling student-related API requests.
 * Provides endpoints for retrieving student information.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentRepository studentRepository;

    /**
     * Constructor for StudentController, injecting the StudentRepository.
     *
     * @param studentRepository Repository for Student entities.
     */
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves a list of all students in the system.
     *
     * @return A ResponseEntity containing a list of Student objects and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        logger.info("Received request to get all students.");
        List<Student> students = studentRepository.findAll();
        logger.debug("Found {} students.", students.size());
        return ResponseEntity.ok(students);
    }

    /**
     * Retrieves a specific student by their unique identifier.
     *
     * @param studentId The unique ID of the student to retrieve.
     * @return A ResponseEntity containing the Student object if found (HTTP status 200 OK),
     *         or HTTP status 404 (Not Found) if no student with the given ID exists.
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        logger.info("Received request to get student by ID: {}", studentId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            logger.debug("Found student with ID: {}", studentId);
            return ResponseEntity.ok(studentOptional.get());
        } else {
            logger.warn("Student with ID {} not found.", studentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves a list of students belonging to a specific class.
     * This endpoint is useful for general student listing per class, distinct from the
     * absence-specific DTO returned by AbsenceController.
     *
     * @param classId The unique ID of the class.
     * @return A ResponseEntity containing a list of Student objects and HTTP status 200 (OK).
     */
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Student>> getStudentsByClassId(@PathVariable String classId) {
        logger.info("Received request to get students for class ID: {}", classId);
        List<Student> students = studentRepository.findByCurrentClass_ClassId(classId);
        logger.debug("Found {} students for class ID: {}", students.size(), classId);
        return ResponseEntity.ok(students);
    }
}