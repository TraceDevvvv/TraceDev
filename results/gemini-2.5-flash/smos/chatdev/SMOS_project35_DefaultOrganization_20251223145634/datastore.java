/**
 * Simulates a data storage system for students and their notes.
 * In a real application, this would interact with a database or persistent storage.
 */
import java.util.ArrayList;
import java.util.List;
public class DataStore {
    // Static lists to hold our in-memory data
    private static List<Student> students = new ArrayList<>();
    private static List<Note> notes = new ArrayList<>();
    // Static initializer block to populate some sample data
    static {
        // Add sample students
        students.add(new Student("S001", "Alice Smith"));
        students.add(new Student("S002", "Bob Johnson"));
        students.add(new Student("S003", "Charlie Brown"));
        students.add(new Student("S004", "Diana Prince"));
        // Add sample notes for students
        notes.add(new Note("S001", "Excellent performance in math. Participates actively.", "2023-09-15"));
        notes.add(new Note("S001", "Struggled a bit with the new science topic, requires extra attention.", "2023-10-01"));
        notes.add(new Note("S001", "Showed great improvement in English writing.", "2023-11-20"));
        notes.add(new Note("S002", "Consistent effort in all subjects. A pleasure to teach.", "2023-09-10"));
        notes.add(new Note("S002", "Needs to work on group collaboration skills.", "2023-10-25"));
        notes.add(new Note("S003", "Struggling with focus during class. Parent meeting recommended.", "2023-09-28"));
        notes.add(new Note("S004", "Top student in the class, always eager to learn.", "2023-10-05"));
        notes.add(new Note("S004", "Helped other students with their assignments during study hall.", "2023-11-12"));
    }
    /**
     * Retrieves a list of all students in the data store.
     * @return A list of Student objects.
     */
    public static List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return a copy to prevent external modification
    }
    /**
     * Retrieves all notes associated with a specific student ID.
     * @param studentId The ID of the student whose notes are to be retrieved.
     * @return A list of Note objects for the given student ID. Returns an empty list if no notes are found.
     */
    public static List<Note> getNotesByStudentId(String studentId) {
        List<Note> studentNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getStudentId().equals(studentId)) {
                studentNotes.add(note);
            }
        }
        return studentNotes;
    }
}