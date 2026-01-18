package com.example;

/**
 * Use case responsible for orchestrating the deletion of a disciplinary note.
 * It handles retrieving note details, sending notifications, deleting the note,
 * and managing transactions.
 */
public class DeleteNoteUseCase {

    private NoteRepositoryPort noteRepository;
    private NotificationServicePort notificationService;
    private TransactionManager transactionManager;
    private Logger logger;

    /**
     * Constructs a DeleteNoteUseCase.
     *
     * @param noteRepository      The repository for accessing note data.
     * @param notificationService The service for sending notifications.
     * @param transactionManager  The transaction manager for ensuring data consistency.
     * @param logger              The logger for recording operation details.
     */
    public DeleteNoteUseCase(NoteRepositoryPort noteRepository,
                             NotificationServicePort notificationService,
                             TransactionManager transactionManager,
                             Logger logger) {
        this.noteRepository = noteRepository;
        this.notificationService = notificationService;
        this.transactionManager = transactionManager;
        this.logger = logger;
    }

    /**
     * Executes the note deletion process.
     * This involves retrieving the note, notifying parents, and then deleting the note,
     * all within a transaction.
     *
     * @param noteId The ID of the note to be deleted.
     * @throws NoteDeletionFailedException if any step in the deletion process fails,
     *                                     including database issues or notification failures.
     */
    public void execute(String noteId) throws NoteDeletionFailedException, NotificationFailedException {
        logger.info("DeleteNoteUseCase: Starting execution for noteId: " + noteId);
        transactionManager.beginTransaction();
        try {
            // 1. Retrieve Note details to get parent email
            Note noteToDelete = noteRepository.findNoteById(noteId);
            if (noteToDelete == null) {
                String errorMessage = "Note not found for ID: " + noteId;
                logger.warn("DeleteNoteUseCase: " + errorMessage);
                throw new NoteDeletionFailedException(errorMessage);
            }
            logger.info("DeleteNoteUseCase: Note '" + noteId + "' found. Parent email: " + noteToDelete.getParentEmail());

            // 2. Send notification to the student's parents
            String parentEmail = noteToDelete.getParentEmail();
            String notificationMessage = "Notification: A disciplinary note concerning your child (ID: "
                    + noteToDelete.getStudentId() + ") has been deleted. "
                    + "Original content: '" + noteToDelete.getContent() + "'.";
            notificationService.sendNotification(parentEmail, notificationMessage);
            logger.info("DeleteNoteUseCase: Notification sent to parent: " + parentEmail);

            // 3. Eliminate the data of the note from the archive
            noteRepository.deleteNote(noteId);
            logger.info("DeleteNoteUseCase: Note '" + noteId + "' successfully deleted from repository.");

            transactionManager.commit();
            logger.info("DeleteNoteUseCase: Successfully completed deletion and notification for noteId: " + noteId);

        } catch (DatabaseException e) {
            transactionManager.rollback();
            logger.error("DeleteNoteUseCase: Database error during note deletion for ID " + noteId + ". Rolling back. " + e.getMessage());
            throw new NoteDeletionFailedException("Failed to process note deletion due to database error for noteId: " + noteId, e);
        } catch (NotificationFailedException e) {
            transactionManager.rollback();
            logger.error("DeleteNoteUseCase: Notification failed during note deletion for ID " + noteId + ". Rolling back. " + e.getMessage());
            // Re-throwing NotificationFailedException as specific exception type as per sequence diagram
            throw e;
        } catch (NoteDeletionFailedException e) {
            transactionManager.rollback();
            logger.error("DeleteNoteUseCase: Note deletion specific failure for ID " + noteId + ". Rolling back. " + e.getMessage());
            throw e;
        } catch (Exception e) {
            transactionManager.rollback();
            logger.error("DeleteNoteUseCase: An unexpected error occurred during note deletion for ID " + noteId + ". Rolling back. " + e.getMessage());
            throw new NoteDeletionFailedException("An unexpected error occurred during note deletion for ID: " + noteId, e);
        }
    }
}