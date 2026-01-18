package domain;

/**
 * Placeholder DTO for displaying a summary of a note in a list.
 * This class was inferred from `NoteListView.displayNoteList(List<NoteSummaryDTO>)`
 * in the class diagram, as `NoteSummaryDTO` itself was not defined.
 */
public class NoteSummaryDTO {
    public String noteId;
    public String studentName;
    public String briefDescription;

    /**
     * Constructs a new NoteSummaryDTO.
     * @param noteId The ID of the note.
     * @param studentName The name of the student associated with the note.
     * @param briefDescription A short description or snippet of the note.
     */
    public NoteSummaryDTO(String noteId, String studentName, String briefDescription) {
        this.noteId = noteId;
        this.studentName = studentName;
        this.briefDescription = briefDescription;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    @Override
    public String toString() {
        return "NoteSummaryDTO{" +
               "noteId='" + noteId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", briefDescription='" + briefDescription + '\'' +
               '}';
    }
}