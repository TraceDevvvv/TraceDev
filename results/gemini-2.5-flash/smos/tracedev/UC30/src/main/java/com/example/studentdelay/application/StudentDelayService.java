package com.example.studentdelay.application;

import com.example.studentdelay.dataaccess.IStudentDelayRepository;
import com.example.studentdelay.dataaccess.StudentDelayDTO;
import com.example.studentdelay.domain.DelayRecord;
import com.example.studentdelay.domain.NotificationService;
import com.example.studentdelay.domain.Student;
import com.example.studentdelay.util.ConnectionException;
import com.example.studentdelay.util.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * StudentDelayService handles the business logic for managing student delays.
 * It orchestrates interactions between the repository and notification service.
 */
public class StudentDelayService {
    private final IStudentDelayRepository studentDelayRepository;
    private final NotificationService notificationService;

    public StudentDelayService(IStudentDelayRepository studentDelayRepository, NotificationService notificationService) {
        this.studentDelayRepository = studentDelayRepository;
        this.notificationService = notificationService;
    }

    /**
     * Retrieves a list of student delay records for a specified date.
     * Converts domain entities (DelayRecord, Student) into DTOs for the presentation layer.
     *
     * @param date The date for which to retrieve delay records.
     * @return A list of StudentDelayDTOs.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    public List<StudentDelayDTO> getDelayRecordsByDate(LocalDate date) throws ConnectionException {
        System.out.println("Service: Getting delay records for date: " + date);
        List<DelayRecord> delayRecords = studentDelayRepository.findByDate(date);

        // Convert DelayRecord entities to StudentDelayDTOs, populating studentName
        List<StudentDelayDTO> delayDTOs = new ArrayList<>();
        for (DelayRecord record : delayRecords) {
            Student student = studentDelayRepository.findStudentById(record.getStudentId());
            String studentName = (student != null) ? student.getName() : "Unknown Student";
            delayDTOs.add(new StudentDelayDTO(
                    record.getId(),
                    record.getStudentId(),
                    studentName,
                    record.getDelayDate(),
                    record.getReason(),
                    record.getEntryTimestamp()
            ));
        }
        System.out.println("Service: Retrieved " + delayDTOs.size() + " DTOs for date " + date);
        return delayDTOs;
    }

    /**
     * Registers a new student delay. This involves validation, persistence,
     * and sending notifications.
     *
     * @param delayData The StudentDelayDTO containing the delay information.
     * @return The registered StudentDelayDTO, including any generated ID.
     * @throws ValidationException if the delay data is invalid.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    public StudentDelayDTO registerStudentDelay(StudentDelayDTO delayData) throws ValidationException, ConnectionException {
        System.out.println("Service: Registering student delay for student ID: " + delayData.studentId);

        // Validate delay data (business logic, integrity - Quality Requirement)
        if (!validateDelayData(delayData)) {
            throw new ValidationException("Invalid delay data provided. Student ID and reason are required.");
        }

        // Convert StudentDelayDTO to DelayRecord entity
        // Note: ID is null as it's a new record, entryTimestamp is set by DelayRecord.create
        DelayRecord newDelayRecord = DelayRecord.create(
                delayData.studentId,
                delayData.delayDate != null ? delayData.delayDate : LocalDate.now(), // Default to today if not provided
                delayData.reason
        );

        // Save the delay record to the repository
        DelayRecord savedRecord = studentDelayRepository.save(newDelayRecord);

        // Find student details for notification and DTO conversion
        Student student = studentDelayRepository.findStudentById(savedRecord.getStudentId());
        if (student == null) {
            // This scenario might indicate data inconsistency if studentId was validated.
            // For now, assume it exists or handle as "Unknown".
            System.err.println("Service: Warning: Student with ID " + savedRecord.getStudentId() + " not found for notification.");
            // We can still proceed with DTO return, just notification might be partial or skipped.
        }

        // Send parent notification
        if (student != null) {
            notificationService.sendParentNotification(student, savedRecord);
        } else {
            System.out.println("Service: Notification skipped for student " + savedRecord.getStudentId() + " as student details are unavailable.");
        }

        // Convert savedRecord entity to StudentDelayDTO for return
        // Populating studentName for the returned DTO
        String studentName = (student != null) ? student.getName() : "Unknown Student";
        StudentDelayDTO registeredDTO = new StudentDelayDTO(
                savedRecord.getId(),
                savedRecord.getStudentId(),
                studentName,
                savedRecord.getDelayDate(),
                savedRecord.getReason(),
                savedRecord.getEntryTimestamp()
        );

        System.out.println("Service: Student delay registered successfully. ID: " + registeredDTO.id);
        return registeredDTO;
    }

    /**
     * Internal method to validate the incoming delay data.
     *
     * @param delayData The StudentDelayDTO to validate.
     * @return true if data is valid, false otherwise.
     */
    private boolean validateDelayData(StudentDelayDTO delayData) {
        // Simple validation: studentId and reason must not be null or empty
        return delayData != null &&
               delayData.studentId != null && !delayData.studentId.trim().isEmpty() &&
               delayData.reason != null && !delayData.reason.trim().isEmpty() &&
               delayData.delayDate != null; // Delay date must also be present
    }
}