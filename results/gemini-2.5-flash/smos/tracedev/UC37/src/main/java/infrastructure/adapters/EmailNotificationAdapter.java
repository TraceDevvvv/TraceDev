package infrastructure.adapters;

import domain.DisciplinaryNote;
import infrastructure.ports.IInfoLookupService;
import infrastructure.ports.INotificationService;

import java.time.LocalDateTime;

/**
 * Adapter implementation for the INotificationService port.
 * This class handles the logic for sending email-based notifications
 * by building a NotificationMessage and publishing it via a producer.
 */
public class EmailNotificationAdapter implements INotificationService {
    private NotificationMessageProducer messageProducer;
    private IInfoLookupService infoLookupService;

    /**
     * Constructs an EmailNotificationAdapter with dependencies on
     * NotificationMessageProducer and IInfoLookupService.
     *
     * @param messageProducer The producer responsible for sending messages to the broker.
     * @param infoLookupService The service for looking up student/teacher information.
     */
    public EmailNotificationAdapter(NotificationMessageProducer messageProducer, IInfoLookupService infoLookupService) {
        this.messageProducer = messageProducer;
        this.infoLookupService = infoLookupService;
        System.out.println("EmailNotificationAdapter: Initialized.");
    }

    /**
     * Sends a disciplinary note notification. This involves
     * looking up additional information (student/teacher names)
     * and then passing a structured message to the message producer.
     *
     * @param note The DisciplinaryNote entity that triggered the notification.
     */
    @Override
    public void sendDisciplinaryNoteNotification(DisciplinaryNote note) {
        System.out.println("NotificationAdapter: Preparing to send notification for note ID: " + note.getId());

        // Look up additional information required for the notification message
        // This makes the notification richer and user-friendly.
        String studentName = infoLookupService.getStudentName(note.getStudentId());
        String teacherName = infoLookupService.getTeacherName(note.getTeacherId());

        // Build the specific notification message DTO
        NotificationMessage notificationMessage = buildNotificationMessage(note, studentName, teacherName);

        // Publish the message using the message producer
        messageProducer.publishNotification(notificationMessage);

        System.out.println("NotificationAdapter: Notification for note ID " + note.getId() + " sent to message producer.");
    }

    /**
     * Builds a NotificationMessage DTO from the DisciplinaryNote and looked-up details.
     * This method is private as it's an internal helper for the adapter.
     *
     * @param note The DisciplinaryNote.
     * @param studentName The name of the student.
     * @param teacherName The name of the teacher.
     * @return A fully constructed NotificationMessage.
     */
    private NotificationMessage buildNotificationMessage(DisciplinaryNote note, String studentName, String teacherName) {
        System.out.println("NotificationAdapter: Building NotificationMessage for student " + studentName);
        return new NotificationMessage(
                note.getId(),
                note.getStudentId(),
                studentName,
                note.getParentEmail(),
                teacherName,
                note.getDescription(),
                LocalDateTime.now() // Timestamp when the notification message is created
        );
    }
}