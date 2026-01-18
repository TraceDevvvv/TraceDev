/**
 * Represents a student entity with notes and absences counts.
 * This class models the student data required for the monitoring system.
 * 
 * Fields:
 * - id: Unique identifier for the student
 * - name: Student's full name
 * - notesCount: Number of notes recorded for the student
 * - absencesCount: Number of absences recorded for the student
 */
class Student {
    private int id;
    private String name;
    private int notesCount;
    private int absencesCount;
    public Student(int id, String name, int notesCount, int absencesCount) {
        this.id = id;
        this.name = name;
        this.notesCount = notesCount;
        this.absencesCount = absencesCount;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getNotesCount() { return notesCount; }
    public int getAbsencesCount() { return absencesCount; }
}