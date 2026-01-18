package com.example.insertdelayadmin.service;

import com.example.insertdelayadmin.exception.ResourceNotFoundException;
import com.example.insertdelayadmin.model.*;
import com.example.insertdelayadmin.repository.AdministratorRepository;
import com.example.insertdelayadmin.repository.DelayRecordRepository;
import com.example.insertdelayadmin.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing delay records.
 * It handles the business logic for creating, retrieving, updating, and deleting delay records.
 * It interacts with {@link StudentRepository}, {@link DelayRecordRepository},
 * {@link AdministratorRepository} and {@link NotificationService}.
 */
@Service
public class DelayService {

    private final StudentRepository studentRepository;
    private final DelayRecordRepository delayRecordRepository;
    private final AdministratorRepository administratorRepository;
    private final NotificationService notificationService;

    /**
     * Constructor for DelayService, injecting required dependencies.
     *
     * @param studentRepository Repository for Student entities.
     * @param delayRecordRepository Repository for DelayRecord entities.
     * @param administratorRepository Repository for Administrator entities.
     * @param notificationService Service for sending notifications.
     */
    @Autowired
    public DelayService(StudentRepository studentRepository,
                        DelayRecordRepository delayRecordRepository,
                        AdministratorRepository administratorRepository,
                        NotificationService notificationService) {
        this.studentRepository = studentRepository;
        this.delayRecordRepository = delayRecordRepository;
        this.administratorRepository = administratorRepository;
        this.notificationService = notificationService;
    }

    /**
     * Creates a new delay record based on the provided request and the administrator who entered it.
     *
     * @param request The {@link DelayEntryRequest} containing student ID, date, and reason.
     * @param adminId The ID of the administrator creating the record.
     * @return The created and saved {@link DelayRecord}.
     * @throws ResourceNotFoundException if the student or administrator is not found.
     * @throws IllegalArgumentException if the provided delay reason is invalid.
     */
    @Transactional
    public DelayRecord createDelayRecord(DelayEntryRequest request, String adminId) {
        // 1. Validate Student
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + request.getStudentId()));

        // 2. Validate Administrator
        Administrator administrator = administratorRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found with ID: " + adminId));

        // 3. Validate DelayReason
        DelayReason reason;
        try {
            reason = DelayReason.valueOf(request.getReason().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid delay reason: " + request.getReason() + ". Must be one of: " +
                    java.util.Arrays.stream(DelayReason.values()).map(Enum::name).collect(Collectors.joining(", ")));
        }

        // 4. Create DelayRecord entity
        DelayRecord delayRecord = new DelayRecord();
        // ID and entryTimestamp are set by @PrePersist
        delayRecord.setStudentId(student.getId());
        delayRecord.setDate(request.getDate());
        delayRecord.setReason(reason);
        delayRecord.setEnteredByAdminId(administrator.getId());
        delayRecord.setParentNotified(false); // Will be set to true after notification is sent

        // 5. Save the DelayRecord
        DelayRecord savedDelayRecord = delayRecordRepository.save(delayRecord);

        // 6. Send notification to parents (asynchronously or synchronously based on implementation)
        // The system design states "the system sent notifications to parents."
        // This call will trigger the notification process.
        notificationService.sendParentNotification(savedDelayRecord);

        // Update the parentNotified flag after sending notification
        savedDelayRecord.setParentNotified(true);
        delayRecordRepository.save(savedDelayRecord); // Save again to update the flag

        return savedDelayRecord;
    }

    /**
     * Retrieves a list of delay records for a specific date, entered by a specific administrator.
     *
     * @param date The date for which to retrieve delay records.
     * @param adminId The ID of the administrator whose records are to be retrieved.
     * @return A list of {@link DelayRecordDTO} objects.
     */
    @Transactional(readOnly = true)
    public List<DelayRecordDTO> getDelayRecordsForDate(LocalDate date, String adminId) {
        List<DelayRecord> delayRecords = delayRecordRepository.findByDateAndEnteredByAdminId(date, adminId);

        // Map DelayRecord entities to DelayRecordDTOs
        return delayRecords.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates the reason for an existing delay record.
     *
     * @param recordId The ID of the delay record to update.
     * @param updatedReason The new reason for the delay.
     * @return The updated {@link DelayRecord}.
     * @throws ResourceNotFoundException if the delay record is not found.
     * @throws IllegalArgumentException if the provided updated reason is invalid.
     */
    @Transactional
    public DelayRecord updateDelayRecord(String recordId, String updatedReason) {
        DelayRecord delayRecord = delayRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Delay record not found with ID: " + recordId));

        DelayReason reason;
        try {
            reason = DelayReason.valueOf(updatedReason.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid delay reason: " + updatedReason + ". Must be one of: " +
                    java.util.Arrays.stream(DelayReason.values()).map(Enum::name).collect(Collectors.joining(", ")));
        }

        delayRecord.setReason(reason);
        return delayRecordRepository.save(delayRecord);
    }

    /**
     * Deletes a delay record by its ID.
     *
     * @param recordId The ID of the delay record to delete.
     * @throws ResourceNotFoundException if the delay record is not found.
     */
    @Transactional
    public void deleteDelayRecord(String recordId) {
        if (!delayRecordRepository.existsById(recordId)) {
            throw new ResourceNotFoundException("Delay record not found with ID: " + recordId);
        }
        delayRecordRepository.deleteById(recordId);
    }

    /**
     * Helper method to convert a {@link DelayRecord} entity to a {@link DelayRecordDTO}.
     * This method fetches associated student and administrator details to populate the DTO.
     *
     * @param delayRecord The {@link DelayRecord} entity to convert.
     * @return A {@link DelayRecordDTO} representing the delay record.
     */
    private DelayRecordDTO convertToDto(DelayRecord delayRecord) {
        DelayRecordDTO dto = new DelayRecordDTO();
        dto.setId(delayRecord.getId());
        dto.setDate(delayRecord.getDate());
        dto.setReason(delayRecord.getReason().name()); // Convert enum to string
        dto.setEntryTimestamp(delayRecord.getEntryTimestamp());
        dto.setParentNotified(delayRecord.isParentNotified());

        // Fetch student name
        studentRepository.findById(delayRecord.getStudentId()).ifPresent(student ->
                dto.setStudentName(student.getName()));

        // Fetch administrator name
        administratorRepository.findById(delayRecord.getEnteredByAdminId()).ifPresent(admin ->
                dto.setEnteredByAdminName(admin.getUsername()));

        return dto;
    }
}