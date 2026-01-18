'''
Represents a disciplinary note with details about the student, parent contact, and the note content.
It includes a unique ID for identification.
'''
/**
 * Represents a disciplinary note for a student.
 */
class DisciplinaryNote {
    private int id;
    private String studentId;
    private String parentContactInfo; // e.g., email address, phone number
    private String noteDetails;
    /**
     * Constructs a new DisciplinaryNote object.
     *
     * @param id                A unique identifier for the note.
     * @param studentId         The ID of the student the note pertains to.
     * @param parentContactInfo Contact information for the student's parents.
     * @param noteDetails       The detailed description of the disciplinary incident.
     */
    public DisciplinaryNote(int id, String studentId, String parentContactInfo, String noteDetails) {
        this.id = id;
        this.studentId = studentId;
        this.parentContactInfo = parentContactInfo;
        this.noteDetails = noteDetails;
    }
    /**
     * Gets the unique ID of the note.
     *
     * @return The note ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the student ID associated with the note.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Gets the parent contact information.
     *
     * @return The parent contact info.
     */
    public String getParentContactInfo() {
        return parentContactInfo;
    }
    /**
     * Gets the detailed description of the note.
     *
     * @return The note details.
     */
    public String getNoteDetails() {
        return noteDetails;
    }
    /**
     * Provides a string representation of the disciplinary note.
     *
     * @return A formatted string with note ID, student ID, and details.
     */
    @Override
    public String toString() {
        return "Note ID: " + id + ", Student ID: " + studentId + ", Details: " + noteDetails.substring(0, Math.min(noteDetails.length(), 50)) + "...";
    }
}