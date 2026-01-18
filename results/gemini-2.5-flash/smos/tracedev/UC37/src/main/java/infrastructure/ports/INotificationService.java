package infrastructure.ports;

import domain.DisciplinaryNote;

/**
 * Port interface for sending notifications related to disciplinary notes.
 * This defines the contract for how the application layer requests notifications.
 */
public interface INotificationService {

    /**
     * Sends a notification regarding a disciplinary note.
     * The specific channel (e.g., email, SMS) is handled by the implementing adapter.
     *
     * @param note The DisciplinaryNote for which to send a notification.
     */
    void sendDisciplinaryNoteNotification(DisciplinaryNote note);
}