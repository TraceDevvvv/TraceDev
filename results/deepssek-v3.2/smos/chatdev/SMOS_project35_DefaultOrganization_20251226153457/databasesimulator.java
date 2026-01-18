'''
Simulates a database for student notes.
In a real application, this would connect to an actual database.
'''
package studentnotessystem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class DatabaseSimulator {
    // Singleton instance
    private static DatabaseSimulator instance;
    private List<Student> students;
    private List<Note> notes;
    private DatabaseSimulator() {
        initializeSampleData();
    }
    /**
     * Get singleton instance of DatabaseSimulator
     */
    public static synchronized DatabaseSimulator getInstance() {
        if (instance == null) {
            instance = new DatabaseSimulator();
        }
        return instance;
    }
    /**
     * Initialize sample data for demonstration
     */
    private void initializeSampleData() {
        // Create sample students
        students = new ArrayList<>();
        students.add(new Student("S001", "John", "Doe", 10));
        students.add(new Student("S002", "Jane", "Smith", 11));
        students.add(new Student("S003", "Michael", "Johnson", 10));
        students.add(new Student("S004", "Emily", "Williams", 12));
        students.add(new Student("S005", "David", "Brown", 11));
        // Create sample notes
        notes = new ArrayList<>();
        LocalDate now = LocalDate.now();
        // Notes for John Doe
        notes.add(new Note("N001", students.get(0), now.minusDays(30), 
            "Mr. Johnson", "Mathematics", 
            "Excellent performance on the algebra test. Shows strong analytical skills.", 
            Note.NoteType.ACADEMIC));
        notes.add(new Note("N002", students.get(0), now.minusDays(15), 
            "Ms. Davis", "Physical Education", 
            "Participated actively in football practice. Good team player.", 
            Note.NoteType.BEHAVIORAL));
        // Notes for Jane Smith
        notes.add(new Note("N003", students.get(1), now.minusDays(25), 
            "Mr. Johnson", "Mathematics", 
            "Struggling with geometry concepts. Recommended extra practice sessions.", 
            Note.NoteType.ACADEMIC));
        notes.add(new Note("N004", students.get(1), now.minusDays(10), 
            "Ms. Wilson", "Science", 
            "Shows great curiosity in chemistry experiments. Always asks thoughtful questions.", 
            Note.NoteType.ACADEMIC));
        // Notes for Michael Johnson
        notes.add(new Note("N005", students.get(2), now.minusDays(20), 
            "Ms. Davis", "Physical Education", 
            "Late to class 3 times this month. Please ensure punctuality.", 
            Note.NoteType.ATTENDANCE));
        notes.add(new Note("N006", students.get(2), now.minusDays(5), 
            "Mr. Thompson", "History", 
            "Excellent participation in class discussions about ancient Rome.", 
            Note.NoteType.ACADEMIC));
        // Notes for Emily Williams
        notes.add(new Note("N007", students.get(3), now.minusDays(40), 
            "Ms. Wilson", "Science", 
            "Demonstrated outstanding project on renewable energy sources.", 
            Note.NoteType.ACADEMIC));
        notes.add(new Note("N008", students.get(3), now.minusDays(12), 
            "Ms. Wilson", "Science", 
            "Helped classmates understand difficult physics concepts.", 
            Note.NoteType.GENERAL));
        // Notes for David Brown
        notes.add(new Note("N009", students.get(4), now.minusDays(8), 
            "Mr. Johnson", "Mathematics", 
            "Need to improve homework completion rate. Currently at 60%.", 
            Note.NoteType.ACADEMIC));
        notes.add(new Note("N010", students.get(4), now.minusDays(2), 
            "Ms. Davis", "Physical Education", 
            "Showed leadership during basketball tournament.", 
            Note.NoteType.BEHAVIORAL));
    }
    /**
     * Get all notes from the database
     */
    public List<Note> getAllNotes() {
        return new ArrayList<>(notes); // Return copy to prevent external modification
    }
    /**
     * Get notes for a specific student by student ID
     */
    public List<Note> getNotesByStudentId(String studentId) {
        List<Note> studentNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getStudent().getStudentId().equals(studentId)) {
                studentNotes.add(note);
            }
        }
        return studentNotes;
    }
    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    /**
     * Simulate server disconnection
     */
    public void disconnect() {
        System.out.println("Disconnected from SMOS server");
    }
}