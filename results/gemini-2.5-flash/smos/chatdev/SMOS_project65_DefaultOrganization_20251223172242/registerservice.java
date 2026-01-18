import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
/*
 * A service class responsible for managing and providing access to register data.
 * This acts as a mock backend for the application, holding data in memory.
 */
public class RegisterService {
    // In-memory storage for students
    private Map<String, Student> students = new HashMap<>();
    // In-memory storage for class registers, keyed by class name and academic year
    private Map<String, ClassRegister> classRegisters = new HashMap<>();
    // In-memory storage for all justifications
    private Map<String, Justification> justifications = new HashMap<>();
    // In-memory storage for all disciplinary notes
    private Map<String, DisciplinaryNote> disciplinaryNotes = new HashMap<>();
    public RegisterService() {
        initializeSampleData();
    }
    /**
     * Initializes sample data for students, classes, and register entries
     * to simulate a pre-existing system state.
     */
    private void initializeSampleData() {
        // Sample Students
        Student s1 = new Student("S001", "Alice Smith");
        Student s2 = new Student("S002", "Bob Johnson");
        Student s3 = new Student("S003", "Charlie Brown");
        Student s4 = new Student("S004", "Diana Prince");
        students.put(s1.getId(), s1);
        students.put(s2.getId(), s2);
        students.put(s3.getId(), s3);
        students.put(s4.getId(), s4);
        // Sample Class Register
        String className = "10A";
        String academicYear = "2023-2024";
        ClassRegister class10A = new ClassRegister(className, academicYear);
        classRegisters.put(className + "-" + academicYear, class10A);
        // Sample Dates
        LocalDate date1 = LocalDate.of(2023, 10, 26);
        LocalDate date2 = LocalDate.of(2023, 10, 27);
        LocalDate date3 = LocalDate.of(2023, 10, 28); // Saturday - assume no class
        LocalDate date4 = LocalDate.of(2023, 10, 30);
        // Register Entries for date1 (Oct 26)
        RegisterEntry re1_1 = new RegisterEntry(s1, AttendanceStatus.PRESENT);
        RegisterEntry re1_2 = new RegisterEntry(s2, AttendanceStatus.ABSENT);
        RegisterEntry re1_3 = new RegisterEntry(s3, AttendanceStatus.DELAYED);
        RegisterEntry re1_4 = new RegisterEntry(s4, AttendanceStatus.PRESENT);
        // Add a justification for s2 on date1
        Justification j1 = new Justification(generateId(), s2.getId(), date1, "Flu symptoms", true);
        justifications.put(j1.getId(), j1);
        re1_2.setJustification(j1);
        // Add a disciplinary note for s3 on date1
        DisciplinaryNote dn1 = new DisciplinaryNote(generateId(), s3.getId(), date1, "Tardiness without valid reason.");
        disciplinaryNotes.put(dn1.getId(), dn1);
        re1_3.addDisciplinaryNote(dn1);
        class10A.addOrUpdateEntry(date1, re1_1);
        class10A.addOrUpdateEntry(date1, re1_2);
        class10A.addOrUpdateEntry(date1, re1_3);
        class10A.addOrUpdateEntry(date1, re1_4);
        // Register Entries for date2 (Oct 27)
        RegisterEntry re2_1 = new RegisterEntry(s1, AttendanceStatus.PRESENT);
        RegisterEntry re2_2 = new RegisterEntry(s2, AttendanceStatus.PRESENT);
        RegisterEntry re2_3 = new RegisterEntry(s3, AttendanceStatus.ABSENT);
        RegisterEntry re2_4 = new RegisterEntry(s4, AttendanceStatus.DELAYED);
        // Add a pending justification for s3 on date2
        Justification j2 = new Justification(generateId(), s3.getId(), date2, "Family emergency", false);
        justifications.put(j2.getId(), j2);
        re2_3.setJustification(j2);
        class10A.addOrUpdateEntry(date2, re2_1);
        class10A.addOrUpdateEntry(date2, re2_2);
        class10A.addOrUpdateEntry(date2, re2_3);
        class10A.addOrUpdateEntry(date2, re2_4);
        // Register Entries for date4 (Oct 30) - All present initially
        RegisterEntry re4_1 = new RegisterEntry(s1, AttendanceStatus.PRESENT);
        RegisterEntry re4_2 = new RegisterEntry(s2, AttendanceStatus.PRESENT);
        RegisterEntry re4_3 = new RegisterEntry(s3, AttendanceStatus.PRESENT);
        RegisterEntry re4_4 = new RegisterEntry(s4, AttendanceStatus.PRESENT);
        class10A.addOrUpdateEntry(date4, re4_1);
        class10A.addOrUpdateEntry(date4, re4_2);
        class10A.addOrUpdateEntry(date4, re4_3);
        class10A.addOrUpdateEntry(date4, re4_4);
    }
    /**
     * Generates a unique ID using UUID.
     * @return A unique String ID.
     */
    private String generateId() {
        return UUID.randomUUID().toString();
    }
    /**
     * Retrieves a list of students for a specific class and academic year.
     * In this mock, it returns all known students as if they are in the class.
     * @param className The name of the class.
     * @param academicYear The academic year.
     * @return A list of Student objects.
     */
    public List<Student> getStudentsForClass(String className, String academicYear) {
        // In a real system, this would query a database
        return new ArrayList<>(students.values());
    }
    /**
     * Retrieves the ClassRegister object for a specific class and academic year.
     * Creates a new one if it doesn't exist (simulating class creation).
     * @param className The name of the class.
     * @param academicYear The academic year.
     * @return The ClassRegister object.
     */
    public ClassRegister getClassRegister(String className, String academicYear) {
        String key = className + "-" + academicYear;
        return classRegisters.computeIfAbsent(key, k -> new ClassRegister(className, academicYear));
    }
    /**
     * Saves or updates a RegisterEntry for a specific date.
     * This will update the internal ClassRegister instance.
     * @param className The class name.
     * @param academicYear The academic year.
     * @param date The date of the entry.
     * @param entry The RegisterEntry to save.
     */
    public void saveRegisterEntry(String className, String academicYear, LocalDate date, RegisterEntry entry) {
        ClassRegister classRegister = getClassRegister(className, academicYear);
        classRegister.addOrUpdateEntry(date, entry);
    }
    /**
     * Adds a new justification to the system and updates the corresponding register entry.
     * Assumes the newJustification object already has a unique ID and its details populated.
     * @param className The class name.
     * @param academicYear The academic year.
     * @param date The date of the justification.
     * @param newJustification The Justification object to add.
     */
    public void addJustification(String className, String academicYear, LocalDate date, Justification newJustification) {
        if (newJustification == null) {
            System.err.println("Cannot add null justification.");
            return;
        }
        // Store the provided justification object directly.
        justifications.put(newJustification.getId(), newJustification);
        ClassRegister classRegister = getClassRegister(className, academicYear);
        List<RegisterEntry> entries = classRegister.getRegisterForDate(date);
        Optional<RegisterEntry> studentEntry = entries.stream()
                .filter(e -> e.getStudent().getId().equals(newJustification.getStudentId())) // Use newJustification.getStudentId()
                .findFirst();
        if (studentEntry.isPresent()) {
            studentEntry.get().setJustification(newJustification); // Set the reference to the new object
            // Optionally, update status if justification is approved and status was ABSENT/DELAYED
            if (newJustification.isApproved() && (studentEntry.get().getStatus() == AttendanceStatus.ABSENT || studentEntry.get().getStatus() == AttendanceStatus.DELAYED)) {
                // In a real system, this might transition to 'Excused Absent' etc., but for simplicity, status remains
                // or a new 'Excused' status could be added. Here we'll just track the justification.
            }
        } else {
            // Should not happen if student is already in the register for that date
            // In a real system, you might create a new register entry here if it implies an "excused" status.
            System.err.println("Warning: Justification added for student not in register for " + date + ". Student ID: " + newJustification.getStudentId());
        }
    }
    /**
     * Updates an existing justification's details.
     * @param justification The Justification object with updated details.
     */
    public void updateJustification(Justification justification) {
        if (justification != null && justifications.containsKey(justification.getId())) {
            justifications.put(justification.getId(), justification);
            // No need to update RegisterEntry specifically here, as it holds a reference.
            // If the register entry was holding a copy, then it would need updating.
        }
    }
    /**
     * Adds a new disciplinary note to the system and updates the corresponding register entry.
     * @param className The class name.
     * @param academicYear The academic year.
     * @param date The date of the disciplinary note.
     * @param studentId The ID of the student.
     * @param description The description of the note.
     * @return The newly created DisciplinaryNote object.
     */
    public DisciplinaryNote addDisciplinaryNote(String className, String academicYear, LocalDate date, String studentId, String description) {
        DisciplinaryNote note = new DisciplinaryNote(generateId(), studentId, date, description);
        disciplinaryNotes.put(note.getId(), note);
        ClassRegister classRegister = getClassRegister(className, academicYear);
        List<RegisterEntry> entries = classRegister.getRegisterForDate(date);
        Optional<RegisterEntry> studentEntry = entries.stream()
                .filter(e -> e.getStudent().getId().equals(studentId))
                .findFirst();
        if (studentEntry.isPresent()) {
            studentEntry.get().addDisciplinaryNote(note);
        } else {
            System.err.println("Warning: Disciplinary note added for student not in register for " + date + ". Student ID: " + studentId);
        }
        return note;
    }
}