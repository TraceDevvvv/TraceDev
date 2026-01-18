/*
Represents a disciplinary note with its attributes such as student name, description, teacher name, and the date the note was made.
It includes an auto-generated ID for unique identification.
*/
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Represents a disciplinary note in the system.
 * Each note has a unique ID, student name, description, teacher name, and a date.
 */
public class DisciplinaryNote {
    // Static counter to generate unique IDs for notes
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private int id;
    private String studentName;
    private String description;
    private String teacherName;
    private LocalDate noteDate;
    /**
     * Constructs a new DisciplinaryNote with auto-generated ID.
     *
     * @param studentName   The name of the student.
     * @param description   The description of the disciplinary action.
     * @param teacherName   The name of the teacher who issued the note.
     * @param noteDate      The date when the note was created.
     */
    public DisciplinaryNote(String studentName, String description, String teacherName, LocalDate noteDate) {
        this.id = ID_GENERATOR.incrementAndGet(); // Assign a unique ID
        this.studentName = studentName;
        this.description = description;
        this.teacherName = teacherName;
        this.noteDate = noteDate;
    }
    /**
     * Constructs a DisciplinaryNote with a specific ID.
     * This constructor is primarily used for reconstructing notes from storage or for testing.
     *
     * @param id            The unique ID of the note.
     * @param studentName   The name of the student.
     * @param description   The description of the disciplinary action.
     * @param teacherName   The name of the teacher who issued the note.
     * @param noteDate      The date when the note was created.
     */
    public DisciplinaryNote(int id, String studentName, String description, String teacherName, LocalDate noteDate) {
        this.id = id;
        this.studentName = studentName;
        this.description = description;
        this.teacherName = teacherName;
        this.noteDate = noteDate;
        // Ensure the ID generator is at least as high as the provided ID
        // This is important if loading existing data with specific IDs
        while (ID_GENERATOR.get() < id) {
            ID_GENERATOR.incrementAndGet();
        }
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getDescription() {
        return description;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public LocalDate getNoteDate() {
        return noteDate;
    }
    // Setters (used during editing)
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public void setNoteDate(LocalDate noteDate) {
        this.noteDate = noteDate;
    }
    @Override
    public String toString() {
        return "DisciplinaryNote{" +
               "id=" + id +
               ", studentName='" + studentName + '\'' +
               ", description='" + description + '\'' +
               ", teacherName='" + teacherName + '\'' +
               ", noteDate=" + noteDate +
               '}';
    }
}