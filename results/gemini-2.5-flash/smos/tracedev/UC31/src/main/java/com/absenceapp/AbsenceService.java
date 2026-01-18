package com.absenceapp;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Application service for managing absence records.
 * Handles business logic, orchestrates repository and notification serv.
 */
public class AbsenceService {
    private IAbsenceRepository absenceRepository;
    private INotificationService notificationService;
    private SRegistrationService registrationService; // REQ-001
    // Dummy student data for simulation, normally fetched from a StudentService/Repository
    private Student dummyStudent1 = new Student("s101", "Alice Smith", "alice.parent@example.com");
    private Student dummyStudent2 = new Student("s102", "Bob Johnson", "bob.parent@example.com");
    private Student dummyStudent3 = new Student("s103", "Charlie Brown", "charlie.parent@example.com");

    /**
     * Constructor for AbsenceService, injecting required dependencies.
     *
     * @param absenceRepository The repository for absence data.
     * @param notificationService The service for sending notifications.
     * @param registrationService The service for checking student registration status.
     */
    public AbsenceService(IAbsenceRepository absenceRepository, INotificationService notificationService, SRegistrationService registrationService) {
        this.absenceRepository = absenceRepository;
        this.notificationService = notificationService;
        this.registrationService = registrationService;
    }

    /**
     * Retrieves all absence records for a given date.
     * Converts Absence domain objects to AbsenceDTOs for presentation.
     *
     * @param date The date to retrieve absences for.
     * @return A list of AbsenceDTOs.
     */
    public List<AbsenceDTO> getAbsencesByDate(Date date) {
        System.out.println("Service: Getting absences for date: " + date);
        List<Absence> absences = absenceRepository.findByDate(date);
        return absences.stream()
                .map(AbsenceDTO::fromAbsence)
                .collect(Collectors.toList());
    }

    /**
     * Saves an absence record. This method acts as a dispatcher, calling
     * addAbsence if it's a new record (ID is null/empty or status is NEW),
     * or updateAbsence if it's an existing record (ID is present).
     *
     * @param absenceDTO The AbsenceDTO to save.
     * @return The saved AbsenceDTO.
     * @throws ApplicationException if there's a problem saving the absence.
     */
    public AbsenceDTO saveAbsence(AbsenceDTO absenceDTO) throws ApplicationException {
        System.out.println("Service: Delegating save absence for ID: " + absenceDTO.id + ", status: " + absenceDTO.status);
        if (absenceDTO.id == null || absenceDTO.id.isEmpty() || AbsenceDTO.STATUS_NEW.equals(absenceDTO.status)) {
            // Treat as an add operation
            return addAbsence(absenceDTO);
        } else {
            // Treat as an update operation
            return updateAbsence(absenceDTO);
        }
    }

    /**
     * Adds a new absence record.
     *
     * @param absenceDTO The AbsenceDTO containing the new absence data.
     * @return The AbsenceDTO of the newly added absence.
     * @throws ApplicationException if there's a problem adding the absence.
     */
    public AbsenceDTO addAbsence(AbsenceDTO absenceDTO) throws ApplicationException {
        System.out.println("Service: Adding new absence for student: " + absenceDTO.studentId);
        // REQ-004: Validate DTO before processing. Dummy check for registration.
        if (!registrationService.isRegistrationCompleted(absenceDTO.studentId)) {
            throw new ApplicationException("Student " + absenceDTO.studentId + " registration not completed.");
        }
        if (absenceDTO.studentId == null || absenceDTO.studentId.isEmpty()) {
            throw new ApplicationException("Student ID cannot be empty for new absence.");
        }

        try {
            // Convert DTO to domain object, allowing Absence constructor to generate ID
            Absence newAbsence = absenceDTO.toAbsence();
            newAbsence.setStatus(AbsenceStatus.PENDING); // Ensure new absences start as PENDING

            Absence savedAbsence = absenceRepository.save(newAbsence); // REQ-003

            // REQ-008: Send notification after successful save
            Student student = getStudentById(savedAbsence.getStudentId());
            if (student != null) {
                notificationService.sendAbsenceModificationNotification(student, student.getParentEmail(), savedAbsence);
            } else {
                System.err.println("Service: Student not found for notification: " + savedAbsence.getStudentId());
            }

            return AbsenceDTO.fromAbsence(savedAbsence);
        } catch (PersistenceException e) {
            // REQ-002: Wrap PersistenceException in ApplicationException
            System.err.println("Service: Persistence error while adding absence: " + e.getMessage());
            throw new ApplicationException("Failed to add absence: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing absence record.
     *
     * @param absenceDTO The AbsenceDTO with updated absence data.
     * @return The AbsenceDTO of the updated absence.
     * @throws ApplicationException if the absence does not exist or there's a problem updating.
     */
    public AbsenceDTO updateAbsence(AbsenceDTO absenceDTO) throws ApplicationException {
        System.out.println("Service: Updating absence ID: " + absenceDTO.id + " for student: " + absenceDTO.studentId);
        if (absenceDTO.id == null || absenceDTO.id.isEmpty()) {
            throw new ApplicationException("Absence ID is required for update operation.");
        }
        // REQ-004: Validate DTO before processing. Dummy check for registration.
        if (!registrationService.isRegistrationCompleted(absenceDTO.studentId)) {
            throw new ApplicationException("Student " + absenceDTO.studentId + " registration not completed.");
        }

        try {
            Optional<Absence> existingAbsenceOpt = absenceRepository.findById(absenceDTO.id);
            if (!existingAbsenceOpt.isPresent()) {
                throw new ApplicationException("Absence with ID " + absenceDTO.id + " not found for update.");
            }

            Absence existingAbsence = existingAbsenceOpt.get();
            // Update fields from DTO, keeping the existing ID
            existingAbsence.update(absenceDTO.date, AbsenceType.valueOf(absenceDTO.type), absenceDTO.reason);
            existingAbsence.setStatus(AbsenceStatus.valueOf(absenceDTO.status)); // Update status as well

            Absence savedAbsence = absenceRepository.save(existingAbsence); // REQ-003

            // REQ-008: Send notification after successful save
            Student student = getStudentById(savedAbsence.getStudentId());
            if (student != null) {
                notificationService.sendAbsenceModificationNotification(student, student.getParentEmail(), savedAbsence);
            } else {
                System.err.println("Service: Student not found for notification: " + savedAbsence.getStudentId());
            }

            return AbsenceDTO.fromAbsence(savedAbsence);
        } catch (PersistenceException e) {
            // REQ-002: Wrap PersistenceException in ApplicationException
            System.err.println("Service: Persistence error while updating absence: " + e.getMessage());
            throw new ApplicationException("Failed to update absence with ID " + absenceDTO.id + ": " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            System.err.println("Service: Invalid enum value in DTO during update: " + e.getMessage());
            throw new ApplicationException("Invalid absence type or status provided for update: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes an absence record by its ID.
     *
     * @param absenceId The ID of the absence to delete.
     * @throws ApplicationException if there's a problem deleting the absence.
     */
    public void deleteAbsence(String absenceId) throws ApplicationException {
        System.out.println("Service: Deleting absence ID: " + absenceId);
        if (absenceId == null || absenceId.isEmpty()) {
            throw new ApplicationException("Absence ID cannot be empty for delete operation.");
        }
        try {
            // Optionally, fetch absence first to get studentId for notification before actual delete
            Optional<Absence> absenceToDeleteOpt = absenceRepository.findById(absenceId);

            absenceRepository.delete(absenceId); // REQ-003

            // If we found the absence before deleting, we could potentially send a 'deletion' notification
            if (absenceToDeleteOpt.isPresent()) {
                Absence deletedAbsencePlaceholder = absenceToDeleteOpt.get();
                // For a 'deleted' notification, we might set a special status or send different message type
                deletedAbsencePlaceholder.setStatus(AbsenceStatus.CANCELED); // Use CANCELED as a proxy for deletion notification
                Student student = getStudentById(deletedAbsencePlaceholder.getStudentId());
                if (student != null) {
                    notificationService.sendAbsenceModificationNotification(student, student.getParentEmail(), deletedAbsencePlaceholder);
                }
            }

        } catch (PersistenceException e) {
            // REQ-002: Wrap PersistenceException in ApplicationException
            System.err.println("Service: Persistence error while deleting absence: " + e.getMessage());
            throw new ApplicationException("Failed to delete absence with ID " + absenceId + ": " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves student information by ID.
     * This is a mock implementation. In a real application, this would
     * likely involve a separate StudentService or StudentRepository.
     *
     * @param studentId The ID of the student.
     * @return The Student object, or null if not found.
     */
    public Student getStudentById(String studentId) {
        System.out.println("Service: Fetching student by ID: " + studentId);
        // Simulate fetching student from a dummy data source
        if (dummyStudent1.getId().equals(studentId)) return dummyStudent1;
        if (dummyStudent2.getId().equals(studentId)) return dummyStudent2;
        if (dummyStudent3.getId().equals(studentId)) return dummyStudent3;
        return null;
    }
}