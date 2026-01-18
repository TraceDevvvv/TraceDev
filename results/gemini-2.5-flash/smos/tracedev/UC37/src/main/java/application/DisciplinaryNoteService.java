package application;

import domain.DisciplinaryNote;
import infrastructure.ports.IDisciplinaryNoteRepository;
import infrastructure.ports.IInfoLookupService;
import infrastructure.ports.INotificationService;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Application Service for managing Disciplinary Notes.
 * This class orchestrates business logic, interacts with repositories,
 * and triggers notifications. It acts as the central point for
 * use cases related to disciplinary notes.
 */
public class DisciplinaryNoteService {
    private IDisciplinaryNoteRepository disciplinaryNoteRepository;
    private INotificationService notificationService;
    private IInfoLookupService infoLookupService;

    /**
     * Constructs a DisciplinaryNoteService with necessary dependencies.
     * These dependencies are interfaces (ports) to decouple from infrastructure concerns.
     *
     * @param disciplinaryNoteRepository The repository for persisting disciplinary notes.
     * @param notificationService The service for sending notifications.
     * @param infoLookupService The service for looking up external information.
     */
    public DisciplinaryNoteService(IDisciplinaryNoteRepository disciplinaryNoteRepository,
                                  INotificationService notificationService,
                                  IInfoLookupService infoLookupService) {
        this.disciplinaryNoteRepository = disciplinaryNoteRepository;
        this.notificationService = notificationService;
        this.infoLookupService = infoLookupService;
        System.out.println("DisciplinaryNoteService: Initialized.");
    }

    /**
     * Creates a new disciplinary note. This method embodies the core business logic
     * for creating a note, including validation, data enrichment, persistence,
     * and triggering notifications.
     *
     * @param studentId The ID of the student involved.
     * @param date The date the disciplinary action occurred.
     * @param teacherId The ID of the teacher issuing the note.
     * @param description A detailed description of the disciplinary incident.
     * @return The newly created and saved DisciplinaryNote entity.
     * @throws IllegalArgumentException if the input data is invalid.
     */
    public DisciplinaryNote createDisciplinaryNote(String studentId, LocalDate date, String teacherId, String description) {
        System.out.println("Service: Creating disciplinary note for student: " + studentId);

        // R16: Ensures integrity of disciplinary notes by validating data.
        validateNoteData(studentId, date, teacherId, description);

        // Look up parent email, which is part of the DisciplinaryNote entity and needed for notifications
        String parentEmail = infoLookupService.getParentEmail(studentId);
        if (parentEmail == null || parentEmail.isEmpty()) {
            System.err.println("Service: Warning - Parent email not found for student " + studentId + ". Using placeholder.");
            parentEmail = "no-parent-email-found@example.com"; // Fallback
        }

        // Create the domain entity
        DisciplinaryNote newNote = new DisciplinaryNote(studentId, date, teacherId, description, parentEmail);
        System.out.println("Service: Created new DisciplinaryNote entity (not yet persisted): " + newNote.getId());

        // Persist the disciplinary note
        DisciplinaryNote savedNote = disciplinaryNoteRepository.save(newNote);
        System.out.println("Service: DisciplinaryNote persisted with ID: " + savedNote.getId());

        // Trigger asynchronous notification (R16 - Timely delivery of notifications facilitated by async)
        // This call typically does not block the current thread waiting for notification delivery.
        notificationService.sendDisciplinaryNoteNotification(savedNote);
        System.out.println("Service: Notification initiated for note ID: " + savedNote.getId());

        return savedNote;
    }

    /**
     * Validates the input data for creating a disciplinary note.
     * This is a private helper method enforcing business rules for data integrity (R16).
     *
     * @param studentId The student ID.
     * @param date The date of the incident.
     * @param teacherId The teacher ID.
     * @param description The description of the incident.
     * @throws IllegalArgumentException if any validation rule is violated.
     */
    private void validateNoteData(String studentId, LocalDate date, String teacherId, String description) {
        System.out.println("Service: Validating note data...");
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future.");
        }
        if (teacherId == null || teacherId.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher ID cannot be null or empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        System.out.println("Service: Note data validated successfully.");
    }
}