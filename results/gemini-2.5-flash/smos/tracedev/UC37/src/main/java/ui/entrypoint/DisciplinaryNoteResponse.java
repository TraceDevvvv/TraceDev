package ui.entrypoint;

/**
 * DTO (Data Transfer Object) for encapsulating the response data
 * after creating a new disciplinary note.
 */
public class DisciplinaryNoteResponse {
    private String noteId;
    private String message;

    /**
     * Constructs a new DisciplinaryNoteResponse.
     *
     * @param noteId The ID of the newly created disciplinary note.
     * @param message A confirmation or status message.
     */
    public DisciplinaryNoteResponse(String noteId, String message) {
        this.noteId = noteId;
        this.message = message;
    }

    // Getters
    public String getNoteId() {
        return noteId;
    }

    public String getMessage() {
        return message;
    }

    // Setters (optional, but often included for DTOs if they are mutable)
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DisciplinaryNoteResponse{" +
               "noteId='" + noteId + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}