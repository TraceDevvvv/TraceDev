'''
Represents a student with a name, a count of notes, and a count of absences.
This class serves as a data model for student records in the monitoring system.
'''
public class Student {
    private String name;
    private int notesCount;
    private int absencesCount;
    /**
     * Constructs a new Student object.
     *
     * @param name The name of the student.
     * @param notesCount The number of disciplinary notes for the student.
     * @param absencesCount The number of absences for the student.
     */
    public Student(String name, int notesCount, int absencesCount) {
        this.name = name;
        this.notesCount = notesCount;
        this.absencesCount = absencesCount;
    }
    /**
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the number of disciplinary notes for the student.
     *
     * @return The count of notes.
     */
    public int getNotesCount() {
        return notesCount;
    }
    /**
     * Returns the number of absences for the student.
     *
     * @return The count of absences.
     */
    public int getAbsencesCount() {
        return absencesCount;
    }
    /**
     * Provides a string representation of the Student object.
     * This is useful for displaying student information in the UI.
     *
     * @return A formatted string with student's name, notes, and absences.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Notes: " + notesCount + ", Absences: " + absencesCount;
    }
}