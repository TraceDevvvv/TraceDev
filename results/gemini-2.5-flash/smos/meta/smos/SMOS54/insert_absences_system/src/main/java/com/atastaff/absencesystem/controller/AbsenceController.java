package com.atastaff.absencesystem.controller;

import com.atastaff.absencesystem.dto.AbsenceRequest;
import com.atastaff.absencesystem.dto.StudentAttendanceDTO;
import com.atastaff.absencesystem.model.Student;
import com.atastaff.absencesystem.repository.StudentRepository;
import com.atastaff.absencesystem.service.AbsenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for handling absence-related API requests.
 * Provides endpoints for retrieving student attendance status and saving absence records.
 */
@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    private static final Logger logger = LoggerFactory.getLogger(AbsenceController.class);

    private final AbsenceService absenceService;
    private final StudentRepository studentRepository;

    /**
     * Constructor for AbsenceController, injecting AbsenceService and StudentRepository.
     *
     * @param absenceService    Service for processing absence-related business logic.
     * @param studentRepository Repository for Student entities.
     */
    public AbsenceController(AbsenceService absenceService, StudentRepository studentRepository) {
        this.absenceService = absenceService;
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves a list of students for a given class, with their default attendance status (present).
     * This endpoint simulates the initial screen where ATA staff can mark absences.
     *
     * @param classId The unique ID of the class.
     * @return A ResponseEntity containing a list of StudentAttendanceDTOs and HTTP status 200 (OK).
     */
    @GetMapping("/class/{classId}/students")
    public ResponseEntity<List<StudentAttendanceDTO>> getStudentsForClass(@PathVariable String classId) {
        logger.info("Received request to get students for class ID: {}", classId);
        List<Student> students = studentRepository.findByCurrentClass_ClassId(classId);

        // Map Student entities to StudentAttendanceDTOs, defaulting to present
        List<StudentAttendanceDTO> studentAttendanceList = students.stream()
                .map(student -> new StudentAttendanceDTO(student.getStudentId(), false)) // Default to present (isAbsent = false)
                .collect(Collectors.toList());

        logger.debug("Found {} students for class ID: {}", studentAttendanceList.size(), classId);
        return ResponseEntity.ok(studentAttendanceList);
    }

    /**
     * Saves absence records based on the provided AbsenceRequest.
     * This endpoint processes the attendance data submitted by the ATA staff.
     *
     * @param request The AbsenceRequest DTO containing class ID and student attendance data.
     * @return A ResponseEntity indicating success (HTTP status 200 OK) or an error (HTTP status 400 Bad Request or 500 Internal Server Error).
     */
    @PostMapping
    public ResponseEntity<String> saveAbsences(@RequestBody AbsenceRequest request) {
        logger.info("Received request to save absences for class ID: {}", request.getClassId());
        try {
            absenceService.processAbsences(request);
            logger.info("Absences saved and notifications processed successfully for class ID: {}", request.getClassId());
            return ResponseEntity.ok("Absence data saved and notifications processed successfully.");
        } catch (IllegalArgumentException e) {
            logger.error("Validation error while saving absences for class ID {}: {}", request.getClassId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while saving absences for class ID {}: {}", request.getClassId(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing absences: " + e.getMessage());
        }
    }
}